package zelimkhan.magomadov.recipes.data.meal

import kotlinx.coroutines.flow.Flow
import zelimkhan.magomadov.recipes.core.Resource
import zelimkhan.magomadov.recipes.data.core.BaseRepository
import zelimkhan.magomadov.recipes.data.meal.local.MealDao
import zelimkhan.magomadov.recipes.data.meal.local.MealLocal
import zelimkhan.magomadov.recipes.data.meal.local.asMealLocal
import zelimkhan.magomadov.recipes.data.meal.remote.MealApi
import zelimkhan.magomadov.recipes.data.meal.remote.apiErrorHandler

class MealRepository(
    private val localDataSource: MealDao,
    private val remoteDataSource: MealApi
) : BaseRepository(remoteDataSource::apiErrorHandler) {

    suspend fun meals(name: String): Resource<List<MealLocal>> {
        return safeApiCall(
            apiCall = { remoteDataSource.meals(name) },
            transform = { response -> response.meals.map { it.asMealLocal() } },
            caching = localDataSource::save,
            result = { localDataSource.get(name) }
        )
    }

    suspend fun meal(id: Long): Resource<MealLocal> {
        return safeApiCall(
            apiCall = { remoteDataSource.meal(id) },
            transform = { response -> response.meals.first().asMealLocal() },
            caching = localDataSource::save,
            result = { localDataSource.get(id) }
        )
    }

    suspend fun addFavorite(id: Long) {
        val meal = localDataSource.get(id)
        localDataSource.update(meal.copy(isFavorite = !meal.isFavorite))
    }

    fun favorites(): Flow<List<MealLocal>> {
        return localDataSource.favorites()
    }
}