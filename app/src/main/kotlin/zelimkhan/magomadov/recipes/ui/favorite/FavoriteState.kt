package zelimkhan.magomadov.recipes.ui.favorite

import zelimkhan.magomadov.recipes.ui.core.model.Meal

data class FavoriteState(
    val mealList: List<Meal> = emptyList()
)