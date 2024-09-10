package hrhera.task.forecast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hrhera.task.forecast.domain.weather.ForecastDTO
import hrhera.task.forecast.utils.getUnixTimeOfStartOfDay

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForecasts(forecasts: List<ForecastDTO>)


    /**
     * Returns a list of forecasts for the given location and time.
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     * @param time The time in Unix timestamp format. If not provided, it defaults to the current day's midnight.
     */
    @Query("SELECT * FROM forecasts where latitude = :lat and longitude = :lon and dt >=:time ")
    suspend fun getForecastsByLocation(
        lat: Double,
        lon: Double,
        time: Long = getUnixTimeOfStartOfDay()
    ): List<ForecastDTO>


    @Query("SELECT * FROM forecasts ")
    suspend fun getForecasts(): List<ForecastDTO>
}