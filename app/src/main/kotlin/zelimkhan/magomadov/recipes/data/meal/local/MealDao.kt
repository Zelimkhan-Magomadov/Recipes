package zelimkhan.magomadov.recipes.data.meal.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(meal: MealLocal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(mealList: List<MealLocal>)

    @Update
    suspend fun update(meal: MealLocal)

    @Update
    suspend fun update(mealList: List<MealLocal>)

    @Query("SELECT * FROM meal WHERE id = :id")
    suspend fun get(id: Long): MealLocal

    @Query("SELECT * FROM meal WHERE title LIKE '%'||:name||'%'")
    suspend fun get(name: String): List<MealLocal>

    @Query("SELECT * FROM meal WHERE isFavorite = 1")
    fun favorites(): Flow<List<MealLocal>>
}