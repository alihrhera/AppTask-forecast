package hrhera.task.forecast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hrhera.task.forecast.domain.cities.City
import hrhera.task.forecast.domain.wether.Forecast
import hrhera.task.forecast.domain.wether.ForecastDTO
import java.util.Calendar

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForecasts(forecasts: List<ForecastDTO>)


    @Query("SELECT * FROM forecasts where latitude = :lat and longitude = :lon and dt >=:time ")
    suspend fun getForecastsByLocation(
        lat: Double,
        lon: Double,
        time: Long = Calendar.getInstance().timeInMillis / 1000
    ): List<ForecastDTO>
}