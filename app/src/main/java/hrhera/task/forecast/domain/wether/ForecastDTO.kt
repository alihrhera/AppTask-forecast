package hrhera.task.forecast.domain.wether

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecasts")
data class ForecastDTO(
    val clouds: Clouds,
    @PrimaryKey val dt: Long,
    val text: String,
    val main: Main,
    val pop: Int,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
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

