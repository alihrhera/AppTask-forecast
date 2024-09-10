package hrhera.task.forecast.utils

import hrhera.task.forecast.domain.weather.DayForecast
import hrhera.task.forecast.domain.weather.DayTemperature
import hrhera.task.forecast.domain.weather.Forecast

fun List<Forecast>.groupForecastsByDate(): List<DayForecast> {
    return this
        .groupBy { it.text.split(" ")[0] } // Group by date (yyyy-MM-dd)
        .map { (date, forecastItems) ->

            // Collect day temperatures
            val dayTemperatures = forecastItems.map { item ->
                DayTemperature(
                    time = item.text.split(" ")[1],
                    temperature = "${item.main.temp}",
                )
            }

            // Create DayForecast for the date
            DayForecast(
                date = date,
                main = forecastItems.first().main,
                visibility = forecastItems.first().visibility,
                weather = forecastItems.first().weather,
                dayTemperatures = dayTemperatures
            )
        }
}