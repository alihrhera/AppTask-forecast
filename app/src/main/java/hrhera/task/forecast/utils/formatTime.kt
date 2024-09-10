package hrhera.task.forecast.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.formatTime(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss") // Input format
    val outputFormatter = DateTimeFormatter.ofPattern("hh:mm a") // Output format
    val parsedTime = LocalTime.parse(this, inputFormatter)
    return parsedTime.format(outputFormatter)
}