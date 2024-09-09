package hrhera.task.forecast.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.core.BaseViewModel
import hrhera.task.forecast.domain.cities.City
import hrhera.task.forecast.domain.usecase.GetCitiesUseCase
import hrhera.task.forecast.domain.usecase.GetForecastUseCase
import hrhera.task.forecast.utils.launchTask
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * [HomeViewModel] is the view model for the [HomeFragment].
 * It uses two use cases [GetCitiesUseCase] and [GetForecastUseCase] to get the cities and the forecast data.
 * It handles the loading state, error messages, and the local data warning.
 * It also handles the selection of the city and the retry button click.
 *
 * @property getCitiesUseCase The use case to get the cities.
 * @property getForecastUseCase The use case to get the forecast data.
 * @property errorMessage The error message to be shown.
 * @property errorMessageVisibility The visibility of the error message.
 * @property localDataWarningVisibility The visibility of the local data warning.
 * @property citiesLoadingVisibility The visibility of the loading state for the cities.
 * @property citiesErrorMessage The error message for the cities.
 * @property cities The list of cities.
 * @property selectedCity The selected city.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase, private val getForecastUseCase: GetForecastUseCase
) : BaseViewModel() {
    private fun getCities() = launchTask { getCitiesUseCase.getCities() }


    val errorMessage = MutableLiveData<String?>()
    val errorMessageVisibility = MutableLiveData<Boolean>()


    /**
     * Initializes the view model.
     * It calls [getCities] and sets up the response handling.
     * It shows the error message if it is not blank.
     * It shows the local data warning if the message is not blank.
     * It sets the list of cities to the response.
     */
    init {
        getCities()
        getCitiesUseCase.response.responseCollect(onError = {
            setError(it)
        }, onSuccess = { response ->
            cities.postValue(response.cities)
            localDataWarningVisibility.postValue(response.message.isNullOrBlank().not())
        })
    }


    val localDataWarningVisibility = MutableLiveData<Boolean>()
    val citiesLoadingVisibility = getCitiesUseCase.response.map { it is BaseResponse.Loading }.asLiveData()
    val citiesErrorMessage = MutableLiveData<String?>()
    val cities = MutableLiveData<List<City>>(emptyList())


    /**
     * Sets the selected city and calls [getForecastData].
     * @param city the selected city
     */
    private var selectedCity: Pair<Double, Double>? = null
    fun setSelectCity(city: Pair<Double, Double>) {
        selectedCity = city
        getForecastData()
    }


    /**
     * Calls [getForecastUseCase.getForecastData] with the selected city and handles the errors.
     * If the selected city is null, it shows the error message.
     * @see [getForecastUseCase.getForecastData]
     */
    private fun getForecastData() = launchTask {
        selectedCity?.let {
            citiesErrorMessage.postValue(null)
            localDataWarningVisibility.postValue(false)

            getForecastUseCase.getForecastData(
                it.first, it.second
            )
        } ?: showMustSelectCityError()

    }


    /**
     * Shows the error message when the user didn't select a city.
     */
    private fun showMustSelectCityError() {
        citiesErrorMessage.postValue("Please select city")
    }


    /**
     * Retries to fetch the forecast data when the user clicks the retry button.
     */
    fun retry() {
        getForecastData()
    }


    /**
     * Collects the response from [getForecastUseCase] and updates the error message and visibility.
     * If the response is not empty, it shows the local data warning.
     */
    init {
        getForecastUseCase.response.responseCollect(onError = { errorMessageValue ->
            setError(errorMessageValue)
        }, onSuccess = { response ->
            localDataWarningVisibility.postValue(response.messageTxt.isNullOrBlank().not())
        })
    }

    private fun setError(errorMessageValue: String) {
        errorMessage.postValue(errorMessageValue)
        errorMessageVisibility.postValue(errorMessageValue.isNotBlank())
    }

    val loadingForecast = getForecastUseCase.response.map { it is BaseResponse.Loading }.asLiveData()

}
