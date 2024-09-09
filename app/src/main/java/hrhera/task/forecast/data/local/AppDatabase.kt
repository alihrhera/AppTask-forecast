package hrhera.task.forecast.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hrhera.task.forecast.domain.cities.City
import hrhera.task.forecast.domain.wether.ForecastDTO

/**
 * Class that provides access to the database for storing the forecast data.
 * It is using a singleton pattern to ensure that there is only one instance of the database.
 */
@Database(
    entities = [City::class, ForecastDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ObjectsMapper::class) // Add converters
abstract class AppDatabase : RoomDatabase() {

    /**
     * Returns a [CitiesDao] instance that can be used to access the cities table.
     */
    abstract fun cityDao(): CitiesDao

    /**
     * Returns a [ForecastDao] instance that can be used to access the forecast table.
     */
    abstract fun forecastDao(): ForecastDao


    companion object {

        private const val DATABASE_NAME = "forecast_app_database"

        private var instance: AppDatabase? = null

        /**
         * Returns the instance of the database.
         * If the instance is null, it creates a new one and stores it in the [instance] variable.
         * It is thread safe.
         */
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return instance!!
        }
    }
}