package zelimkhan.magomadov.recipes.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import zelimkhan.magomadov.recipes.data.meal.MealRepository
import zelimkhan.magomadov.recipes.data.meal.local.MealLocal
import zelimkhan.magomadov.recipes.ui.core.base.BaseViewModel
import zelimkhan.magomadov.recipes.ui.core.extension.asLiveData
import zelimkhan.magomadov.recipes.ui.core.model.asMeal
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : BaseViewModel() {
    private val _menuState = MutableLiveData<MenuState>()
    val menuState = _menuState.asLiveData

    private var job: Job? = null

    fun loaMeals(name: String) {
        job?.cancel()
        job = viewModelScope.launch {
            handleRequest(
                request = { mealRepository.meals(name) },
                transform = { it.asMenuState },
                onSuccess = { data -> _menuState.value = MenuState(mealList = data) }
            )
        }
    }

    private val List<MealLocal>.asMenuState get() = this.map { it.asMeal() }
}