package hrhera.task.forecast.domain.wether

data class ForecastResponseData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)