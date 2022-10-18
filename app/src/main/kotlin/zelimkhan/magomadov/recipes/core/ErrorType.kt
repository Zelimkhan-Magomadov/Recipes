package zelimkhan.magomadov.recipes.core

sealed interface ErrorType {
    object NetworkConnection : ErrorType
    object ServerError : ErrorType
    class ApiError(val message: Int) : ErrorType
}