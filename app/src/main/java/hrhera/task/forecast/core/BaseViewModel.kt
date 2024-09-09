package hrhera.task.forecast.core

import androidx.lifecycle.ViewModel
import hrhera.task.forecast.utils.launchTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

abstract class BaseViewModel : ViewModel() {
    fun <T : Any> Flow<BaseResponse<T>>.responseCollect(
        onError: (String) -> Unit = {},
        onSuccess: (T) -> Unit
    ) {
        launchTask {
            this@responseCollect.collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {}
                    is BaseResponse.Body -> {
                        onSuccess(response.data)
                    }

                    is BaseResponse.Error -> {

                    }

                    is BaseResponse.None -> Unit

                }
            }
        }
    }
}