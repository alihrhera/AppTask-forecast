package hrhera.task.forecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hrhera.task.forecast.data.api.CitiesApiService
import hrhera.task.forecast.data.api.ForecastApiService
import hrhera.task.forecast.data.local.AppDatabase
import hrhera.task.forecast.data.local.CitiesDao
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CitiesDaoProviderModule {


    @Provides
    @Singleton
    fun provideCitiesDao(database: AppDatabase): CitiesDao =
        database.cityDao()




}