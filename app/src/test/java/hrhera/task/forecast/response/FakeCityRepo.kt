package hrhera.task.forecast.response

import hrhera.task.forecast.core.BaseErrorServerResponse
import hrhera.task.forecast.core.BaseRepository
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.data.repo.CitiesRepository
import hrhera.task.forecast.domain.cities.CitiesResponse
import hrhera.task.forecast.domain.cities.City
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCityRepo : BaseRepository(), CitiesRepository {

    private val cities = mutableListOf<City>(
        City("City 1", "City 1", 10, 10.0, 10.0),
        City("City 1", "City 1", 11, 10.0, 10.0),
        City("City 1", "City 1", 12, 10.0, 10.0),
    )
    private var networkError = false

    fun setNetworkError(value: Boolean) {
        networkError = value
    }

    override val citiesResponse = MutableStateFlow<BaseResponse<CitiesResponse>>(BaseResponse.None)

    override suspend fun getCities() {
        if (networkError) {
            if (cities.isEmpty()) {
                citiesResponse.value = BaseResponse.Error(Throwable("Network Error"), BaseErrorServerResponse(0, "Network Error"))

            } else {
                citiesResponse.value = BaseResponse.Body(CitiesResponse(cities, "This is local data"))
            }
        } else {
            citiesResponse.value = BaseResponse.Body(CitiesResponse(cities, null))
        }
    }


}