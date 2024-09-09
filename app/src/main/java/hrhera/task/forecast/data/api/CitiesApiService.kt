package hrhera.task.forecast.data.api

import hrhera.task.forecast.domain.cities.CitiesResponse
import retrofit2.http.GET

interface CitiesApiService {
    @GET("cities.json")
    suspend fun getCities(): CitiesResponse
}
