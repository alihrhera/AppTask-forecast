package hrhera.task.forecast.core

import androidx.annotation.Keep
import okhttp3.Headers

@Keep
sealed class BaseResponse<out T : Any> {
    @Keep
    data class Body<out T : Any>(val data: T) : BaseResponse<T>()

    @Keep
    data class HeaderAndBody<out T : Any>(val header: Headers, val data: T) : BaseResponse<T>()

    @Keep
    data class Error(val throwable: Throwable, val errorBody: BaseErrorServerResponse) : BaseResponse<Nothing>()

    @Keep
    data class Loading(val loading: Boolean) : BaseResponse<Nothing>()

    @Keep
    object None : BaseResponse<Nothing>()
}