package cu.jesusd0897.farmakit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.holder.GenericViewHolder
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct
import cu.jesusd0897.farmakit.util.KUtil

class NaturalProductAdapter(
    val interactionListener: OnInteractionListener<MinNaturalProduct>, layoutId: Int
) :
    ModelMarkAdapter<MinNaturalProduct>(interactionListener, layoutId)/*, PopupTextProvider*/ {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): GenericViewHolder<MinNaturalProduct> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return NaturalProductViewHolder(view)
    }

    //override fun getPopupText(position: Int): String = getItem(position).name[0].toString()

    inner class NaturalProductViewHolder internal constructor(view: View) :
        GenericViewHolder<MinNaturalProduct>(view, interactionListener) {

        private val title: AppCompatTextView = view.findViewById(R.id.title_item)
        private val subtitle: AppCompatTextView = view.findViewById(R.id.subtitle_item)
        private val description: AppCompatTextView = view.findViewById(R.id.content_item)
        private val icon: AppCompatImageView = view.findViewById(R.id.icon_item)

        override fun bind(model: MinNaturalProduct) {
            title.text = model.name
            subtitle.text = model.scientificName
            description.text = model.category
            icon.setColorFilter(
                if (model.isFavorite) KUtil.getThemeColor(itemView.context, R.attr.colorAccent)
                else KUtil.getThemeColor(itemView.context, R.attr.itemTextColor),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        override fun getModel(position: Int): MinNaturalProduct = getItem(position)

    }

}