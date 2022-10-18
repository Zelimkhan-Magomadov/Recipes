package zelimkhan.magomadov.recipes.ui.meal

import zelimkhan.magomadov.recipes.data.meal.local.MealLocal

data class MealState(
    val mealImage: String = "",
    val instructions: String = ""
)

val MealLocal.asMealState get() = MealState(mealImage = mealImage, instructions = instructions)
