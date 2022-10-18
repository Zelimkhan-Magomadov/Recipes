package zelimkhan.magomadov.recipes.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.databinding.ActivityMainBinding
import zelimkhan.magomadov.recipes.ui.core.navigation.Navigation

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val topLevelDestinations = setOf(R.id.menu, R.id.favorite, R.id.profile)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavConfig()
    }

    private fun setupNavConfig() {
        navController = findNavController(R.id.hostFragment)
        binding.bottomNavigation.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(topLevelDestinations)
    }

    override fun connectWithToolbar(toolbar: Toolbar) {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}