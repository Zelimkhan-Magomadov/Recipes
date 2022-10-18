package zelimkhan.magomadov.recipes.ui.meal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import zelimkhan.magomadov.recipes.data.meal.MealRepository
import zelimkhan.magomadov.recipes.ui.core.base.BaseViewModel
import zelimkhan.magomadov.recipes.ui.core.extension.asLiveData
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _mealState = MutableLiveData<MealState>()
    val mealState = _mealState.asLiveData

    private val meadId = savedStateHandle.get<Long>("mealId")!!
    private var job: Job? = null

    init {
        loadMeal()
    }

    fun loadMeal() {
        if (job?.isActive == true) return
        job = viewModelScope.launch {
            handleRequest(
                request = { mealRepository.meal(meadId) },
                transform = { it.asMealState },
                onSuccess = { data -> _mealState.value = data }
            )
        }
    }
}