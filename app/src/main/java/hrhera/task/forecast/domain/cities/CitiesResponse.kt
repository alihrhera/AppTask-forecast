package hrhera.task.forecast.domain.cities

import androidx.room.Entity
import androidx.room.PrimaryKey


data class CitiesResponse(val cities: List<City>, val message: String?)