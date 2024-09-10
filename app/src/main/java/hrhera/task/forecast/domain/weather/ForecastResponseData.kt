package hrhera.task.forecast.domain.weather

import com.google.gson.annotations.SerializedName

data class ForecastResponseData(
    val city: ForecastCity,
    val cnt: Int,
    val cod: String,
    @SerializedName("list")
    val forecasts: List<Forecast>,
    val message: Int,
    val messageTxt: String?
)

fun Forecast.toForecastDto(city: ForecastCity, latitude: Double, longitude: Double): ForecastDTO {
    return ForecastDTO(
        clouds, dt, text, main, pop, sys, visibility, weather, wind,
        city.coord, city.country, city.id, city.name, city.population,
        city.sunrise, city.sunset, city.timezone, latitude, longitude
    )
}
