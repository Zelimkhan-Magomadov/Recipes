package zelimkhan.magomadov.recipes.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import zelimkhan.magomadov.recipes.data.meal.local.MealDao
import zelimkhan.magomadov.recipes.data.meal.local.MealLocal

@Database(
    entities = [MealLocal::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val mealDao: MealDao

    companion object {
        const val DATABASE_NAME = "meal_db"
    }
}