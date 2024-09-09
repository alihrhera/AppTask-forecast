package hrhera.task.forecast.data.api

import hrhera.task.forecast.BuildConfig
import hrhera.task.forecast.domain.wether.ForecastResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {

    @GET("forecast")
   suspend fun getForecastData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int=10,
        @Query("appid") appId: String = BuildConfig.API_KEY
    ): ForecastResponseData
}
