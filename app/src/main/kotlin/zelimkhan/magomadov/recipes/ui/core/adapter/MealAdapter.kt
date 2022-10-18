package zelimkhan.magomadov.recipes.ui.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import zelimkhan.magomadov.recipes.R
import zelimkhan.magomadov.recipes.ui.core.base.BaseViewHolder
import zelimkhan.magomadov.recipes.ui.core.model.Meal

class MealAdapter : ListAdapter<Meal, BaseViewHolder<Meal>>(MenuDiffCallback()) {
    interface Listener {
        fun onMealClick(mealTitle: String, mealId: Long)

        fun onFavoriteClick(mealId: Long)
    }

    var listener: Listener? = null

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_meal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Meal> {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_meal -> MealViewHolder(itemView, listener)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Meal>, position: Int) {
        holder.bind(getItem(position))
    }
}