package cu.jesusd0897.farmakit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.holder.GenericViewHolder
import cu.jesusd0897.farmakit.model.Tool

class ToolAdapter(val interactionListener: OnInteractionListener<Tool>, layoutId: Int) :
    ModelMarkAdapter<Tool>(interactionListener, layoutId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Tool> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ToolViewHolder(view)
    }

    inner class ToolViewHolder internal constructor(view: View) :
        GenericViewHolder<Tool>(view, interactionListener) {

        private val title: AppCompatTextView = view.findViewById(R.id.title_item)
        private val subtitle: AppCompatTextView = view.findViewById(R.id.subtitle_item)
        private val icon: AppCompatImageView = view.findViewById(R.id.icon_item)

        override fun bind(model: Tool) {
            title.text = itemView.context.getText(model.name)
            subtitle.text = itemView.context.getText(model.subtitle)
            icon.setImageResource(model.icon)
        }

        override fun getModel(position: Int): Tool = getItem(position)

    }

}