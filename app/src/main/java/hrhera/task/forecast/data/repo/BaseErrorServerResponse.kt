package hrhera.task.forecast.data.repo
import androidx.annotation.Keep

@Keep
data class BaseErrorServerResponse(val statusCode:Int, val errorMessage:String)
