package cu.jesusd0897.farmakit.adapter

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import cu.jesusd0897.farmakit.adapter.holder.GenericViewHolder
import cu.jesusd0897.farmakit.model.Model
import java.util.*

abstract class ModelMarkAdapter<M : Model?> internal constructor(
    val clickListener: OnInteractionListener<M>, @field:LayoutRes @param:LayoutRes var layoutId: Int
) : RecyclerView.Adapter<GenericViewHolder<M>>(){

    private var models: MutableList<M> = ArrayList()

    override fun onBindViewHolder(holder: GenericViewHolder<M>, position: Int) =
        holder.bind(models[position])

    override fun getItemCount(): Int = models.size

    fun getItem(position: Int): M = models[position]

    fun getItems(): MutableList<M> = models

    fun setItems(models: MutableList<M>) {
        this.models = models
        notifyDataSetChanged()
    }

}