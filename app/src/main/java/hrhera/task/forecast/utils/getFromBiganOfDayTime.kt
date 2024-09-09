package hrhera.task.forecast.utils

import java.util.Calendar

/**
 * Returns the current day's Unix timestamp (in seconds) at midnight.
 *
 * Note that this method is not DST-safe, as it uses the system's local timezone.
 */
fun getUnixTimeOfStartOfDay(): Long {
    return with(Calendar.getInstance()) {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        timeInMillis / 1000
    }
}