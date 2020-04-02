package cu.jesusd0897.farmakit.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cu.jesusd0897.farmakit.adapter.OnInteractionListener

abstract class GenericViewHolder<M> internal constructor(
    itemView: View, private val interactionListener: OnInteractionListener<M>
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { interactionListener.onClick(getModel(adapterPosition)) }
        /*itemView.setOnLongClickListener {
            interactionListener.onLongClick(getModel(adapterPosition))
            return@setOnLongClickListener true
        }*/
    }

    abstract fun bind(model: M)
    abstract fun getModel(position: Int): M

}