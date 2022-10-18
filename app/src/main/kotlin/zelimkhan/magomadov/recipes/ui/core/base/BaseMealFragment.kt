package zelimkhan.magomadov.recipes.ui.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.ui.core.adapter.MealAdapter

@AndroidEntryPoint
abstract class BaseMealFragment(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {
    protected val mealAdapter = MealAdapter()
    private val viewModel: BaseMealViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMealAdapter()
    }

    private fun setupMealAdapter() {
        mealAdapter.listener = object : MealAdapter.Listener {

            override fun onMealClick(mealTitle: String, mealId: Long) {
                findNavController().navigate(
                    R.id.action_to_mealFragment,
                    bundleOf("mealTitle" to mealTitle, "mealId" to mealId)
                )
            }

            override fun onFavoriteClick(mealId: Long) {
                viewModel.addFavorite(mealId)
            }
        }
    }
}