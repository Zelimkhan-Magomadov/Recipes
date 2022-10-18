package zelimkhan.magomadov.recipes.data.core

import retrofit2.HttpException
import zelimkhan.magomadov.recipes.core.ErrorType
import zelimkhan.magomadov.recipes.core.Resource
import java.io.IOException

abstract class BaseRepository(
    private val apiErrorHandler: (code: Int) -> Int
) {
    protected suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> T,
        transform: suspend (T) -> R,
        caching: suspend (R) -> Unit,
        result: suspend () -> R
    ): Resource<R> {
        return try {
            val transformedResult = transform(apiCall())
            caching.invoke(transformedResult)
            Resource.Success(result())
        } catch (e: Exception) {
            when (e) {
                is IOException -> Resource.Error(
                    type = ErrorType.NetworkConnection,
                    data = result()
                )
                is HttpException -> Resource.Error(
                    type = ErrorType.ApiError(message = apiErrorHandler(e.code())),
                    data = result()
                )
                else -> Resource.Error(
                    type = ErrorType.ServerError,
                    data = result()
                )
            }
        }
    }
}