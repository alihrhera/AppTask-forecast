package hrhera.task.forecast.domain.weather

import hrhera.task.forecast.utils.getDayName

data class DayForecast(
    val date: String,
    val main: Main,
    val visibility: Double,
    val weather: List<Weather>,
    val dayTemperatures: List<DayTemperature>
) {
    val dayName get() = date.getDayName()

    val getWeatherStatus = weather.firstOrNull()?.let { "${it.main} - ${it.description}" } ?: ""
}
