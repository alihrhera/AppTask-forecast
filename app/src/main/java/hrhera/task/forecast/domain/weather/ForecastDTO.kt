package hrhera.task.forecast.domain.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecasts")
data class ForecastDTO(
    val clouds: Clouds,
    @PrimaryKey val dt: Long,
    val text: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Double,
    val weather: List<Weather>,
    val wind: Wind,
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Long,
    val sunrise: Long,
    val sunset: Long,
    val timezone: Int,
    val latitude: Double,
    val longitude: Double,
)

fun ForecastDTO.toForecast(): Forecast {
    return Forecast(clouds, dt, text, main, pop, sys, visibility, weather, wind)
}
fun ForecastDTO.toForecastCity(): ForecastCity {
    return ForecastCity(coord, country, id, name, population, sunrise, sunset, timezone)
}

