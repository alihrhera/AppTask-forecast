package hrhera.task.forecast.domain.usecase

import hrhera.task.forecast.data.repo.CitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    val response = repository.citiesResponse
    suspend fun getCities() = repository.getCities()
}