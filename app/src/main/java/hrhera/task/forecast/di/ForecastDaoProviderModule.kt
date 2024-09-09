package hrhera.task.forecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hrhera.task.forecast.data.api.CitiesApiService
import hrhera.task.forecast.data.api.ForecastApiService
import hrhera.task.forecast.data.local.AppDatabase
import hrhera.task.forecast.data.local.CitiesDao
import hrhera.task.forecast.data.local.ForecastDao
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastDaoProviderModule {


    @Provides
    @Singleton
    fun provideForecastDao(database: AppDatabase): ForecastDao = database.forecastDao()
}