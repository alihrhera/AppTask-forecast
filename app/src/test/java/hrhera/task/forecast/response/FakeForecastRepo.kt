package hrhera.task.forecast.response

import hrhera.task.forecast.core.BaseErrorServerResponse
import hrhera.task.forecast.core.BaseRepository
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.data.repo.ForecastRepository
import hrhera.task.forecast.domain.weather.Clouds
import hrhera.task.forecast.domain.weather.Coord
import hrhera.task.forecast.domain.weather.ForecastCity
import hrhera.task.forecast.domain.weather.ForecastDTO
import hrhera.task.forecast.domain.weather.ForecastResponseData
import hrhera.task.forecast.domain.weather.Main
import hrhera.task.forecast.domain.weather.Sys
import hrhera.task.forecast.domain.weather.Weather
import hrhera.task.forecast.domain.weather.Wind
import hrhera.task.forecast.domain.weather.toForecast
import kotlinx.coroutines.flow.MutableStateFlow

class FakeForecastRepo : BaseRepository(), ForecastRepository {

    private val forecast = mutableListOf<ForecastDTO>(
        ForecastDTO(
            clouds = Clouds(all = 75),
            dt = 1638316800, // Example timestamp
            text = "Clear sky",
            main = Main(
                temp = 25.0,
                feels_like = 26.5,
                temp_min = 22.0,
                temp_max = 27.0,
                pressure = 1012,
                humidity = 60,
                sea_level = 1013,
                grnd_level = 1012,
                temp_kf = 0.0
            ),
            pop = 0.1,
            sys = Sys(
              "CST",
            ),
            visibility = 10000.0,
            weather = listOf(
                Weather(id = 800, main = "Clear", description = "clear sky", icon = "01d")
            ),
            wind = Wind(speed = 5.0, deg = 270, gust = 10.0),
            coord = Coord(lat = 34.05, lon = -118.25),
            country = "US",
            id = 123456,
            name = "Los Angeles",
            population = 1000000,
            sunrise = 1638298800,
            sunset = 1638342000,
            timezone = -28800,
            latitude = 34.05,
            longitude = -118.25
        ),

    )
    private var networkError = false

    fun setNetworkError(value: Boolean) {
        networkError = value
    }


    override val forecastResponse = MutableStateFlow<BaseResponse<ForecastResponseData>>(
        BaseResponse
            .None
    )

    override suspend fun getForecastData(lon: Double, lat: Double) {
        buildTask {
            if (networkError) {
                if (forecast.none { item -> item.coord.lat == lon && item.coord.lat == lat }) {
                    forecastResponse.value =
                        BaseResponse.Error(Throwable("Network Error"), BaseErrorServerResponse(0, "Network Error"))
                } else {
                    forecastResponse.value = BaseResponse.Body(
                        ForecastResponseData(
                            city = ForecastCity(
                                coord = forecast.first().coord,
                                country = "EG",
                                id = 1,
                                name = "Cairo",
                                population = 100,
                                sunrise = 100,
                                sunset = 100,
                                timezone = 100
                            ),
                            cnt = 1,
                            cod = "200",
                            forecasts = forecast.map { it.toForecast() },
                            message = 0,
                            messageTxt = "this local weather data"
                        )
                    )
                }
            } else {
                forecastResponse.value = BaseResponse.Body(
                    ForecastResponseData(
                        city = ForecastCity(
                            coord = forecast.first().coord,
                            country = "EG",
                            id = 1,
                            name = "Cairo",
                            population = 100,
                            sunrise = 100,
                            sunset = 100,
                            timezone = 100
                        ),
                        cnt = 1,
                        cod = "200",
                        forecasts = forecast.map { it.toForecast() },
                        message = 0,
                        messageTxt = ""
                    )
                )
            }
        }
    }
}