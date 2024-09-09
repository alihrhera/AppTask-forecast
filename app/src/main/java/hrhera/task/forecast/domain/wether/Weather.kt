package hrhera.task.forecast.domain.wether

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)