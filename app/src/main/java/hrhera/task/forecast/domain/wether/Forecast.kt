package hrhera.task.forecast.domain.wether

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Forecast(
    val clouds: Clouds,
    @PrimaryKey val dt: Long,
    @SerializedName("dt_txt")
    val text: String,
    val main: Main,
    val pop: Int,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
