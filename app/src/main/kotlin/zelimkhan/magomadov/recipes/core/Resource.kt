package zelimkhan.magomadov.recipes.core

sealed interface Resource<T> {
    class Success<T>(val data: T) : Resource<T>
    class Error<T>(val type: ErrorType, val data: T?) : Resource<T>
}