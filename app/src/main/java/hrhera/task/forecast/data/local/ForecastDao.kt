package hrhera.task.forecast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hrhera.task.forecast.domain.cities.City

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCitiesList(cities: List<City>)


    @Query("SELECT * FROM cities")
    suspend fun getAllUsers(): List<City>
}