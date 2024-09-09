package hrhera.task.forecast.domain.usecase

import hrhera.task.forecast.data.repo.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) {

    val response = repository.forecastResponse
    suspend fun getForecastData(latitude: Double, longitude: Double) =
        repository.getForecastData(lat = latitude, lon = longitude)
}