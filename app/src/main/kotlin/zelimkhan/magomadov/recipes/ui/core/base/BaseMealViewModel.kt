package zelimkhan.magomadov.recipes.ui.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import zelimkhan.magomadov.recipes.data.meal.MealRepository
import javax.inject.Inject

@HiltViewModel
class BaseMealViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    fun addFavorite(mealId: Long) {
        viewModelScope.launch {
            mealRepository.addFavorite(mealId)
        }
    }
}