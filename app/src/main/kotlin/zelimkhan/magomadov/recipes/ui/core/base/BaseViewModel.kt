package zelimkhan.magomadov.recipes.ui.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zelimkhan.magomadov.recipes.core.ErrorType
import zelimkhan.magomadov.recipes.core.Resource
import zelimkhan.magomadov.recipes.ui.core.extension.asLiveData

abstract class BaseViewModel : ViewModel() {
    private val _error = MutableLiveData<ErrorType>()
    val error = _error.asLiveData

    protected suspend fun <T, R> handleRequest(
        request: suspend () -> Resource<T>,
        transform: (T) -> R,
        onSuccess: (data: R) -> Unit
    ) {
        when (val result = request()) {
            is Resource.Success -> onSuccess(transform(result.data))
            is Resource.Error -> {
                _error.value = result.type
                if (result.data == null) return
                onSuccess(transform(result.data))
            }
        }
    }
}