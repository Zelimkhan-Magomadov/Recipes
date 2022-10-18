package zelimkhan.magomadov.recipes.ui.favorite

import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import zelimkhan.magomadov.recipes.data.meal.MealRepository
import zelimkhan.magomadov.recipes.data.meal.local.MealLocal
import zelimkhan.magomadov.recipes.ui.core.base.BaseViewModel
import zelimkhan.magomadov.recipes.ui.core.model.asMeal
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    mealRepository: MealRepository
) : BaseViewModel() {

    val favoriteState = liveData {
        val flow = mealRepository.favorites().map { it.asFavoriteState }
        emitSource(flow.asLiveData())
    }

    private val List<MealLocal>.asFavoriteState get() = FavoriteState(this.map { it.asMeal() })
}
