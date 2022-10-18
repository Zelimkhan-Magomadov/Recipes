package zelimkhan.magomadov.recipes.ui.core.adapter

import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import zelimkhan.magomadov.recipes.databinding.ItemMealBinding
import zelimkhan.magomadov.recipes.ui.core.base.BaseViewHolder
import zelimkhan.magomadov.recipes.ui.core.model.Meal

class MealViewHolder(
    itemView: View,
    listener: MealAdapter.Listener?
) : BaseViewHolder<Meal>(itemView) {
    private val binding = ItemMealBinding.bind(itemView)
    private lateinit var menuState: Meal

    init {
        binding.card.setOnClickListener {
            listener?.onMealClick(menuState.title, menuState.id)
        }

        binding.favorite.setOnClickListener {
            listener?.onFavoriteClick(menuState.id)
        }
    }

    override fun bind(data: Meal) {
        this.menuState = data
        if (!binding.card.isVisible) binding.root.transitionToStart()
        Glide.with(binding.root).load(data.mealImage).into(binding.mealImage)
        binding.title.text = data.title
        binding.ingredients.text = data.ingredients
        binding.country.text = data.country
        binding.category.text = data.category
        binding.favorite.isChecked = data.isFavorite
    }
}