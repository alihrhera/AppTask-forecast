package hrhera.task.forecast.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.domain.cities.CitiesResponse
import hrhera.task.forecast.domain.cities.City
import hrhera.task.forecast.domain.usecase.GetCitiesUseCase
import hrhera.task.forecast.domain.usecase.GetForecastUseCase
import hrhera.task.forecast.domain.wether.ForecastResponseData
import io.mockk.MockK
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private val getCitiesUseCase = mockk<GetCitiesUseCase>()

    private val getForecastUseCase: GetForecastUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        every { getCitiesUseCase.response } returns MutableStateFlow(BaseResponse.None)
        every { getForecastUseCase.response } returns MutableStateFlow(BaseResponse.None)

        viewModel = HomeViewModel(getCitiesUseCase, getForecastUseCase)
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
        coEvery { getCitiesUseCase.response } returns MutableStateFlow(
            BaseResponse.Body(
                CitiesResponse(
                    listOf(
                        City(
                            "Cairo",
                            "Cairo",
                            1,
                            1.0,
                            1.0,
                        ),
                    ), null
                )
            )
        )
        viewModel.getCities()

        // Assert: Check if the response flow is updated correctly
        val response = viewModel.getCitiesUseCase.response.value
        assertTrue(response is BaseResponse.Body)
        assertEquals(1, (response as BaseResponse.Body).data.cities.size)
        assertEquals("Cairo", response.data.cities[0].cityNameEn)
    }


    @Test
    fun `getForecastData should fetch forecast if city is selected`() = runTest {
        // Given
        val latitude = 30.100
        val longitude = 30.200

        val selectedCity = Pair(latitude, longitude)
        viewModel.selectedCity = selectedCity

        // Mock the use case to return a successful result
        coEvery { getForecastUseCase.getForecastData(latitude, longitude) } returns mockk()

        // When
        viewModel.getForecastData()

        // Then
        coVerify(exactly = 1) { getForecastUseCase.getForecastData(latitude, longitude) }
        assertNull(viewModel.citiesErrorMessage.value) // Make sure error message is cleared
        assertEquals(false, viewModel.localDataWarningVisibility.value)
    }

    @Test
    fun `getForecastData should show error when no city is selected`() = runTest {
        // Given
        viewModel.selectedCity = null

        // Mock the behavior of showing the error
        val spyViewModel = spyk(viewModel)
        every { spyViewModel.showMustSelectCityError() } just Runs

        // When
        spyViewModel.getForecastData()

        // Then
        verify { spyViewModel.showMustSelectCityError() }
        coVerify(exactly = 0) { getForecastUseCase.getForecastData(any(), any()) }
    }



}