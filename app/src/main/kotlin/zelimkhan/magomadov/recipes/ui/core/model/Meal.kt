package zelimkhan.magomadov.recipes.ui.core.model

import zelimkhan.magomadov.recipes.data.meal.local.MealLocal

data class Meal(
    val id: Long = 0,
    val mealImage: String? = null,
    val title: String = "",
    val ingredients: String = "",
    val country: String = "",
    val category: String = "",
    val isFavorite: Boolean = false
)

fun MealLocal.asMeal(): Meal {
    return Meal(
        id = id,
        mealImage = mealImage,
        title = title,
        country = country,
        category = category,
        ingredients = ingredients,
        isFavorite = isFavorite
    )
}
