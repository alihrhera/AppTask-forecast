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


    private val citiesResponse = MutableStateFlow<BaseResponse<CitiesResponse>>(BaseResponse.None)
    suspend fun getCities() {
        citiesResponse.value = BaseResponse.Loading(true)

        buildTask { api.getCities() }.collectLatest { response ->
            when (response) {
                is BaseResponse.Body -> {
                    citiesDao.saveCitiesList(response.data.cities)
                    citiesResponse.value = BaseResponse.Body(
                        CitiesResponse(
                            response.data.cities,
                            null
                        )
                    )
                }
                is BaseResponse.Error -> {
                    val localCities = citiesDao.getAllCities()
                    citiesResponse.value = BaseResponse.Body(
                        CitiesResponse(
                            localCities,
                            if (localCities.isEmpty()) response.errorBody.errorMessage
                            else "This is local data"
                        )
                    )
                }

                else -> {
                    citiesResponse.value = response
                }
            }
        }
    }


}