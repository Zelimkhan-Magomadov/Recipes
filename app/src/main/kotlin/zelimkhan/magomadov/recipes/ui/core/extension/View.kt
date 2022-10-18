package zelimkhan.magomadov.recipes.ui.core.extension

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

val ChipGroup.checkedChipText get() = findViewById<Chip>(checkedChipId).text.toString()