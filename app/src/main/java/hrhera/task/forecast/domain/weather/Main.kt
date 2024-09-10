package hrhera.task.forecast.domain.weather

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
) {
    val getMaxTemperature get() = "${temp_max}°C"

    val getMainTemperature get() = "${temp_min}°C"
}
