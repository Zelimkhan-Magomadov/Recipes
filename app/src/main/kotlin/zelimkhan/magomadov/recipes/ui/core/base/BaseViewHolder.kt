package zelimkhan.magomadov.recipes.ui.core.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    private val itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}