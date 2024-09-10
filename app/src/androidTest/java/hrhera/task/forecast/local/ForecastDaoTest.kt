package hrhera.task.forecast.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import hrhera.task.forecast.data.local.AppDatabase
import hrhera.task.forecast.data.local.ForecastDao
import hrhera.task.forecast.domain.cities.City
import hrhera.task.forecast.domain.weather.Clouds
import hrhera.task.forecast.domain.weather.Coord
import hrhera.task.forecast.domain.weather.Forecast
import hrhera.task.forecast.domain.weather.ForecastDTO
import hrhera.task.forecast.domain.weather.Main
import hrhera.task.forecast.domain.weather.Sys
import hrhera.task.forecast.domain.weather.Weather
import hrhera.task.forecast.domain.weather.Wind
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.fixedRateTimer

@RunWith(AndroidJUnit4::class)
@SmallTest
class ForecastDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var forecastDao: ForecastDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        forecastDao = db.forecastDao()
    }


    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetForecast() = runBlocking {
        val forecast = listOf(
            ForecastDTO(
                Clouds(0),
                1643723400,
                "test",
                Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0),
                0.0,
                Sys(""),
                0.0,
                listOf(Weather("", "test", 111, "test")),
                Wind(0, 0.0, 0.0),
                Coord(0.0, 0.0),
                "test",
                1,
                "test",
                0,
                0,
                0,
                0,
                0.0,
                0.0
            ),
            ForecastDTO(
                Clouds(0),
                1643856400,
                "test",
                Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0),
                0.0,
                Sys(""),
                0.0,
                listOf(Weather("", "test", 111, "test")),
                Wind(0, 0.0, 0.0),
                Coord(0.0, 0.0),
                "test",
                1,
                "test",
                0,
                0,
                0,
                0,
                0.0,
                0.0
            ),
            ForecastDTO(
                Clouds(0),
                16438584560,
                "test",
                Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0),
                0.0,
                Sys(""),
                0.0,
                listOf(Weather("", "test", 111, "test")),
                Wind(0, 0.0, 0.0),
                Coord(0.0, 0.0),
                "test",
                1,
                "test",
                0,
                0,
                0,
                0,
                0.0,
                0.0
            ),

            )

        forecastDao.saveForecasts(forecast)

        val forecastFromDb = forecastDao.getForecasts()

        assert(forecastFromDb.containsAll(forecast))
    }


    @Test
    fun insertAndGetForecastsByLocation() = runBlocking {
        val forecast = listOf(
            ForecastDTO(
                Clouds(0),
                1643723400,
                "test",
                Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0),
                0.0,
                Sys(""),
                0.0,
                listOf(Weather("", "test", 111, "test")),
                Wind(0, 0.0, 0.0),
                Coord(0.0, 0.0),
                "test",
                1,
                "test",
                0,
                0,
                0,
                0,
                1.0,
                1.0
            ),
            ForecastDTO(
                Clouds(0),
                1643856400,
                "test",
                Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0),
                0.0,
                Sys(""),
                0.0,
                listOf(Weather("", "test", 111, "test")),
                Wind(0, 0.0, 0.0),
                Coord(0.0, 0.0),
                "test",
                1,
                "test",
                0,
                0,
                0,
                0,
                0.0,
                0.0
            ),
            ForecastDTO(
                Clouds(0),
                16438584560,
                "test",
                Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0),
                0.0,
                Sys(""),
                0.0,
                listOf(Weather("", "test", 111, "test")),
                Wind(0, 0.0, 0.0),
                Coord(0.0, 0.0),
                "test",
                1,
                "test",
                0,
                0,
                0,
                0,
                0.0,
                0.0
            ),
            )


        forecastDao.saveForecasts(forecast)
        val forecastFromDb = forecastDao.getForecastsByLocation(1.0, 1.0,1643723300)
        assert(forecastFromDb.size==1)
    }
}