/*
 * Copyright (c) 2020 jesusd0897.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cu.jesusd0897.farmakit.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.holder.GenericViewHolder
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct
import cu.jesusd0897.farmakit.util.KUtil

class ProductAdapter(
    val interactionListener: OnInteractionListener<MinProduct>, layoutId: Int
) :
    ModelMarkAdapter<MinProduct>(interactionListener, layoutId)/*, PopupTextProvider*/ {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): GenericViewHolder<MinProduct> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ProductViewHolder(view)
    }

    //override fun getPopupText(position: Int): String = getItem(position).name[0].toString()

    inner class ProductViewHolder internal constructor(view: View) :
        GenericViewHolder<MinProduct>(view, interactionListener) {

        private val title: AppCompatTextView = view.findViewById(R.id.title_item)
        private val subtitle: AppCompatTextView = view.findViewById(R.id.subtitle_item)
        private val description: AppCompatTextView = view.findViewById(R.id.content_item)
        private val icon: AppCompatImageView = view.findViewById(R.id.icon_item)

        override fun bind(model: MinProduct) {
            title.text = model.name
            subtitle.text =
                model.presentation + if (TextUtils.isEmpty(model.presentationAmount)) "" else " " + model.presentationAmount
            description.text =
                if (TextUtils.isEmpty(model.category)) itemView.context.getString(R.string.no_category) else model.category
            icon.setImageResource(KUtil.getPresentationIcon(model.presentation))
            icon.setColorFilter(
                if (model.isFavorite) KUtil.getThemeColor(itemView.context, R.attr.colorAccent)
                else KUtil.getThemeColor(itemView.context, R.attr.itemTextColor),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        override fun getModel(position: Int): MinProduct = getItem(position)

    }

}