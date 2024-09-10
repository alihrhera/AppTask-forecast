package hrhera.task.forecast.domain.weather

data class ForecastCity(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Long,
    val sunrise: Long,
    val sunset: Long,
    val timezone: Int
)