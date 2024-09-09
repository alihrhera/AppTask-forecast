package hrhera.task.forecast.core
import androidx.annotation.Keep

@Keep
data class BaseErrorServerResponse(val statusCode:Int, val errorMessage:String)
