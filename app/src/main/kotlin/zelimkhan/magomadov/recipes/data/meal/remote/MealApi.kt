package zelimkhan.magomadov.recipes.data.meal.remote

import retrofit2.http.GET
import retrofit2.http.Query
import zelimkhan.magomadov.recipes.R

interface MealApi {
    @GET("search.php?")
    suspend fun meals(@Query("s") name: String): MealApiResponse

    @GET("lookup.php")
    suspend fun meal(@Query("i") id: Long): MealApiResponse
}

fun MealApi.apiErrorHandler(code: Int): Int {
    return R.string.api_error
}