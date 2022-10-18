package zelimkhan.magomadov.recipes.ui.core.adapter

import androidx.recyclerview.widget.DiffUtil
import zelimkhan.magomadov.recipes.ui.core.model.Meal

class MenuDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}