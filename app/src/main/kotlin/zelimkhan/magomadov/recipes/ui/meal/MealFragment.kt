package zelimkhan.magomadov.recipes.ui.meal

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.core.ErrorType
import zelimkhan.magomadov.recipes.databinding.FragmentMealBinding
import zelimkhan.magomadov.recipes.ui.core.extension.userMessage

@AndroidEntryPoint
class MealFragment : Fragment(R.layout.fragment_meal) {
    private lateinit var binding: FragmentMealBinding
    private val viewModel: MealViewModel by viewModels()
    private val args: MealFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMealBinding.bind(view)
        setupTopAppBar()
        setupSwipeRefresh()
        setupStateObserver()
    }

    private fun setupTopAppBar() {
        binding.topAppBar.toolbar.title = args.mealTitle
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadMeal()
        }
    }

    private fun setupStateObserver() {
        viewModel.error.observe(viewLifecycleOwner) { type ->
            binding.swipeRefresh.isRefreshing = false
            when (type) {
                is ErrorType.NetworkConnection -> userMessage(R.string.network_connection_error)
                is ErrorType.ServerError -> userMessage(R.string.server_error)
                is ErrorType.ApiError -> userMessage(type.message)
            }
        }

        viewModel.mealState.observe(viewLifecycleOwner) { state ->
            binding.swipeRefresh.isRefreshing = false
            if (state == null) {
                binding.swipeRefresh.isVisible = false
                binding.errorText.isVisible = true
            } else {
                binding.swipeRefresh.isVisible = true
                binding.errorText.isVisible = false
                Glide.with(this@MealFragment).load(state.mealImage).into(binding.mealImage)
                binding.instructions.text = state.instructions
            }
        }
    }
}