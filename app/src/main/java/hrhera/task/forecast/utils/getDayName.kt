package hrhera.task.forecast.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


fun String.getDayName(): String {
    val date = LocalDate.parse(this) // Parse the string to LocalDate
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()) // Get the day name
}