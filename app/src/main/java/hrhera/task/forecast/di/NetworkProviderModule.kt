package hrhera.task.forecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hrhera.task.forecast.data.api.CitiesApiService
import hrhera.task.forecast.data.api.ForecastApiService
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkProviderModule {


    @Provides
    @Singleton
    fun provideForecastApiService(@Named("forecast") retrofit: Retrofit): ForecastApiService =
        retrofit.create(ForecastApiService::class.java)


    @Provides
    @Singleton
    fun provideCitiesApiService(@Named("cities") retrofit: Retrofit): CitiesApiService =
        retrofit.create(CitiesApiService::class.java)

}