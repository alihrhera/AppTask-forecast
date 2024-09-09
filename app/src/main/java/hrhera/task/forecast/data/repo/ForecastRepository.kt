package hrhera.task.forecast.data.repo

import hrhera.task.forecast.core.BaseRepository
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.data.api.ForecastApiService
import hrhera.task.forecast.data.local.ForecastDao
import hrhera.task.forecast.domain.wether.ForecastResponseData
import hrhera.task.forecast.domain.wether.toForecast
import hrhera.task.forecast.domain.wether.toForecastCity
import hrhera.task.forecast.domain.wether.toForecastDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ForecastRepository
@Inject constructor(
    private val api: ForecastApiService, private val forecastDao: ForecastDao
) : BaseRepository() {


     val forecastResponse = MutableStateFlow<BaseResponse<ForecastResponseData>>(
        BaseResponse
            .None
    )

    /**
     * Gets the forecast data from remote server, if remote server is not available,
     * gets forecast data from local database. If local database is empty,
     * shows error message.
     *
     * @param lon the longitude of the location
     * @param lat the latitude of the location
     */
    suspend fun getForecastData(lon: Double, lat: Double) {
        forecastResponse.value = BaseResponse.Loading(true)

        buildTask { api.getForecastData(lon, lat) }.collectLatest { response ->
            when (response) {
                is BaseResponse.Body -> {
                    forecastDao.saveForecasts(response.data.forecasts.map { it.toForecastDto(response.data.city, lat, lon) })
                    forecastResponse.value = response
                }

                is BaseResponse.Error -> {
                    val localData = forecastDao.getForecastsByLocation(lat, lon)
                    if (localData.isNotEmpty()) {
                        val city = localData.first().toForecastCity()
                        forecastResponse.value = BaseResponse.Body(
                            ForecastResponseData(
                                city = city,
                                cnt = localData.size,
                                cod = "200",
                                forecasts = localData.map { it.toForecast() },
                                message = 0,
                                messageTxt = "this is local forecast data"
                            )
                        )

                    } else forecastResponse.value = response

                }

                else -> {
                    forecastResponse.value = response
                }
            }
        }
    }


}