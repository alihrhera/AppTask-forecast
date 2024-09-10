package hrhera.task.forecast.local

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import hrhera.task.forecast.data.local.AppDatabase
import hrhera.task.forecast.data.local.CitiesDao
import hrhera.task.forecast.domain.cities.City
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CitiesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var citiesDao: CitiesDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        citiesDao = db.cityDao()
    }


    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetCities() = runBlocking {
        val cities = listOf(
            City("London", "London", 1, 12.345, 67.890),
            City("London", "London", 2, 128.345, 66.890),
            City("London", "London", 2, 128.345, 66.890),
        )

        citiesDao.saveCitiesList(cities)

        val allCities = citiesDao.getAllCities()

        assert(allCities.containsAll(cities))
    }
}