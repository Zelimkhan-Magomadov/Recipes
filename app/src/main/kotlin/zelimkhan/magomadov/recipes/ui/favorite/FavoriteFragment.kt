package zelimkhan.magomadov.recipes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.databinding.FragmentFavoriteBinding
import zelimkhan.magomadov.recipes.ui.core.base.BaseMealFragment
import zelimkhan.magomadov.recipes.ui.core.extension.connectToolbarToNavigationController

@AndroidEntryPoint
class FavoriteFragment : BaseMealFragment(R.layout.fragment_favorite) {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        connectToolbarToNavigationController(binding.topAppBar.toolbar)
        setupFavoritesList()
        setupStateObserver()
    }

    private fun setupFavoritesList() {
        binding.favoriteList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mealAdapter
        }
    }

    private fun setupStateObserver() {
        viewModel.favoriteState.observe(viewLifecycleOwner) { state ->
            mealAdapter.submitList(state.mealList)
            binding.emptyListLabel.isVisible = state.mealList.isEmpty()
        }
    }
}