package hrhera.task.forecast.utils

import hrhera.task.forecast.BuildConfig

object NetworkConstants {
    const val CACHE_SIZE: Long = (10 * 1024 * 1024).toLong()// 10 MB

    const val timeOut = 3000L


    const val FORECAST_BASE_URL = BuildConfig.FORECAST_BASE_URL
    const val CITIES_BASE_URL = BuildConfig.CITIES_BASE_URL

    const val NO_INTERNET = 10
    const val BAD_INTERNET = 11



}