package zelimkhan.magomadov.recipes.ui.menu

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.core.ErrorType
import zelimkhan.magomadov.recipes.databinding.FragmentMenuBinding
import zelimkhan.magomadov.recipes.ui.core.base.BaseMealFragment
import zelimkhan.magomadov.recipes.ui.core.extension.checkedChipText
import zelimkhan.magomadov.recipes.ui.core.extension.connectToolbarToNavigationController
import zelimkhan.magomadov.recipes.ui.core.extension.userMessage

@AndroidEntryPoint
class MenuFragment : BaseMealFragment(R.layout.fragment_menu) {
    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)
        connectToolbarToNavigationController(binding.toolbar)
        setupChips()
        setupMealList()
        setupSwipeRefresh()
        setupStateObserver()
    }

    private fun setupChips() {
        viewModel.loaMeals(binding.chips.chipGroup.checkedChipText)
        binding.chips.chipGroup.setOnCheckedStateChangeListener { group, _ ->
            viewModel.loaMeals(group.checkedChipText)
        }
    }

    private fun setupMealList() {
        binding.mealList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mealAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loaMeals(binding.chips.chipGroup.checkedChipText)
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

        viewModel.menuState.observe(viewLifecycleOwner) { state ->
            binding.swipeRefresh.isRefreshing = false
            mealAdapter.submitList(state.mealList)
            binding.errorText.isVisible = state.mealList.isEmpty()
        }
    }
}
