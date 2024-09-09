package hrhera.task.forecast.data.repo

import hrhera.task.forecast.utils.NetworkConstants.BAD_INTERNET
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

abstract class BaseRepository() {


    private val defaultDispatcher = Dispatchers.IO


    protected suspend fun <T : Any> buildTask(task: suspend () -> T) = flow<BaseResponse<T>> {
        emit(BaseResponse.Body(data = task()))
    }
        .flowOn(defaultDispatcher)
        .onStart {
            emit(BaseResponse.Loading(loading = true))
        }
        .catch { throwable ->
            emit(BaseResponse.Error(throwable = throwable, errorBody = getErrorBody(throwable)))
        }




    private fun getErrorBody(throwable: Throwable): BaseErrorServerResponse = when (throwable) {
        is SocketTimeoutException -> BaseErrorServerResponse(BAD_INTERNET, "")
        is HttpException -> {
            BaseErrorServerResponse(
                throwable.response()?.code() ?: 0,
                throwable.response()?.errorBody()?.string() ?: throwable.message ?: ""
            )
        }

        else -> BaseErrorServerResponse(
            0,
            throwable.cause?.message ?: throwable.message ?: ""
        )

    }
}