package zelimkhan.magomadov.recipes.ui.core.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import zelimkhan.magomadov.recipes.ui.core.navigation.Navigation

fun Fragment.connectToolbarToNavigationController(toolbar: Toolbar) {
    activity?.lifecycleScope?.launchWhenCreated {
        (requireActivity() as Navigation).connectWithToolbar(toolbar)
    }
}

fun Fragment.userMessage(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Fragment.userMessage(@StringRes text: Int) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}