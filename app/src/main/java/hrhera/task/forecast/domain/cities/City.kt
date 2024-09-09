package hrhera.task.forecast.domain.cities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cities")
data class City(
    val cityNameAr: String,
    val cityNameEn: String,
    @PrimaryKey val id: Int,
    val lat: Double,
    val lon: Double
)