package hrhera.task.forecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hrhera.task.forecast.data.api.CitiesApiService
import hrhera.task.forecast.data.api.ForecastApiService
import hrhera.task.forecast.data.local.CitiesDao
import hrhera.task.forecast.data.local.ForecastDao
import hrhera.task.forecast.data.repo.CitiesRepository
import hrhera.task.forecast.data.repo.CitiesRepositoryImpl
import hrhera.task.forecast.data.repo.ForecastRepository
import hrhera.task.forecast.data.repo.ForecastRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCitiesRepository(citiesApi: CitiesApiService, citiesDao: CitiesDao): CitiesRepository {
        return CitiesRepositoryImpl(citiesApi, citiesDao)
    }

    @Provides
    fun provideForecastRepository(forecastApiService: ForecastApiService, forecastDao: ForecastDao): ForecastRepository {
        return ForecastRepositoryImpl(forecastApiService, forecastDao)
    }
}