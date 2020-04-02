package cu.jesusd0897.farmakit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.holder.GenericViewHolder
import cu.jesusd0897.farmakit.model.Library

class LibraryAdapter(val interactionListener: OnInteractionListener<Library>, layoutId: Int) :
    ModelMarkAdapter<Library>(interactionListener, layoutId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Library> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return LibraryViewHolder(view)
    }

    inner class LibraryViewHolder internal constructor(view: View) :
        GenericViewHolder<Library>(view, interactionListener) {

        private val title: AppCompatTextView = view.findViewById(R.id.title_item)
        private val subtitle: AppCompatTextView = view.findViewById(R.id.subtitle_item)

        override fun bind(model: Library) {
            title.text = itemView.context.getText(model.name)
            subtitle.text = itemView.context.getText(model.link)
        }

        override fun getModel(position: Int): Library = getItem(position)

    }

}