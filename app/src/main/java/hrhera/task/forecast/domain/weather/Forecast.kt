package hrhera.task.forecast.domain.weather

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Forecast(
    val clouds: Clouds,
    @PrimaryKey val dt: Long,
    @SerializedName("dt_txt")
    val text: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Double,
    val weather: List<Weather>,
    val wind: Wind
)
