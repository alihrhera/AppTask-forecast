package hrhera.task.forecast.data.repo

import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.domain.cities.CitiesResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface CitiesRepository {
    val citiesResponse: MutableStateFlow<BaseResponse<CitiesResponse>>
    suspend fun getCities()

}