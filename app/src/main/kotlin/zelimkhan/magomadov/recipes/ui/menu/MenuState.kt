package zelimkhan.magomadov.recipes.ui.menu

import zelimkhan.magomadov.recipes.ui.core.model.Meal

data class MenuState(
    val mealList: List<Meal> = emptyList()
)