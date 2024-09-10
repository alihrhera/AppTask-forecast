package hrhera.task.forecast.features.home

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.domain.cities.CitiesResponse
import hrhera.task.forecast.domain.cities.City
import hrhera.task.forecast.domain.usecase.GetCitiesUseCase
import hrhera.task.forecast.domain.usecase.GetForecastUseCase
import hrhera.task.forecast.response.FakeCityRepo
import hrhera.task.forecast.response.FakeForecastRepo
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P]) // Use the SDK version appropriate for your app
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private val getCitiesUseCase = GetCitiesUseCase(FakeCityRepo())

    private val getForecastUseCase = GetForecastUseCase(FakeForecastRepo())
    private val testDispatcher = StandardTestDispatcher()
    private val daysForecastAdapter = mock(DaysForecastAdapter::class.java) // Mock final class

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(getCitiesUseCase, getForecastUseCase)
        viewModel.adapter=daysForecastAdapter

    }

    @After
    fun tearDown() {
        // Reset the Main dispatcher after the test
        Dispatchers.resetMain() // Clears the Main dispatcher
        testDispatcher.cancel()  // Cancels any coroutines that may still be running
    }


    @Test
    fun `test setError sets error message and visibility`() {
        viewModel.setError("error message")
        assert(viewModel.errorMessage.value == "error message")
        assert(viewModel.errorMessageVisibility.value == true)
    }


    @Test
    fun `test fetchCities success response`() = runBlocking {
        // Arrange: Stub the use case to return a successful response
        viewModel.getCities()

        // Assert: Check if the response flow is updated correctly
        val response = viewModel.getCitiesUseCase.response.value
        assertTrue(response is BaseResponse.Body)
        assertEquals(3, (response as BaseResponse.Body).data.cities.size)
        assertEquals(10, response.data.cities[0].id)
    }


    @Test
    fun `getForecastData should fetch forecast if city is selected`() = runTest {
        // Given
        val latitude = 34.05
        val longitude = -118.25

        val selectedCity = Pair(latitude, longitude)
        viewModel.selectedCity = selectedCity

        // When
        viewModel.getForecastData()
        // Then
        assertNull(viewModel.citiesErrorMessage.value) // Make sure error message is cleared
        assertEquals(false, viewModel.localDataWarningVisibility.value)
    }




}