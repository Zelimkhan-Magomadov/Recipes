package zelimkhan.magomadov.recipes.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.databinding.FragmentProfileBinding
import zelimkhan.magomadov.recipes.ui.core.extension.connectToolbarToNavigationController

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        connectToolbarToNavigationController(binding.topAppBar.toolbar)
    }
}