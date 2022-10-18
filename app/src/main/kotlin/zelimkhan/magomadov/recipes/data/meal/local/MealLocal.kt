package zelimkhan.magomadov.recipes.data.meal.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import zelimkhan.magomadov.recipes.data.meal.remote.MealRemote

@Entity(tableName = "meal")
data class MealLocal(
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    val category: String = "",
    val country: String = "",
    val instructions: String = "",
    val mealImage: String = "",
    val ingredients: String = "",
    val isFavorite: Boolean = false
)

fun MealRemote.asMealLocal(): MealLocal {
    val ingredientsList = listOf(
        ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
        ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
        ingredient11, ingredient12, ingredient13, ingredient14, ingredient15,
        ingredient16, ingredient17, ingredient18, ingredient19, ingredient20,
    ).filterNot { it.isNullOrBlank() }

    return MealLocal(
        id = id,
        mealImage = mealImage,
        title = title,
        country = country,
        category = category,
        instructions = instructions,
        ingredients = ingredientsList.toString()
    )
}