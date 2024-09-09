package hrhera.task.forecast.data.repo

import hrhera.task.forecast.core.BaseRepository
import hrhera.task.forecast.core.BaseResponse
import hrhera.task.forecast.data.api.CitiesApiService
import hrhera.task.forecast.data.local.CitiesDao
import hrhera.task.forecast.domain.cities.CitiesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class CitiesRepository
@Inject constructor(
    private val api: CitiesApiService, private val citiesDao: CitiesDao
) : BaseRepository() {


     val citiesResponse = MutableStateFlow<BaseResponse<CitiesResponse>>(BaseResponse.None)
    /**
     * Get cities from remote server, if server is not available,
     * get cities from local database. If local database is empty,
     * show error message.
     */
    suspend fun getCities() {
        citiesResponse.value = BaseResponse.Loading(true)
        buildTask { api.getCities() }.collectLatest { response ->
            when (response) {
                is BaseResponse.Body -> {
                    // Save cities to local database
                    citiesDao.saveCitiesList(response.data.cities)
                    // Show cities from remote server
                    citiesResponse.value = response
                }
                is BaseResponse.Error -> {
                    // Get cities from local database
                    val localCities = citiesDao.getAllCities()
                    if (localCities.isNotEmpty()) {
                        // Show cities from local database
                        citiesResponse.value = BaseResponse.Body(
                            CitiesResponse(
                                localCities,
                                "This is local data"
                            )
                        )
                    } else {
                        // Show error message
                        citiesResponse.value = response
                    }
                }
                else -> {
                    citiesResponse.value = response
                }
            }
        }
    }
}