package hrhera.task.forecast.data.repo

import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.domain.weather.ForecastResponseData
import kotlinx.coroutines.flow.MutableStateFlow

interface ForecastRepository {
    val forecastResponse: MutableStateFlow<BaseResponse<ForecastResponseData>>
    suspend fun getForecastData(lon: Double, lat: Double)

}