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

package cu.jesusd0897.farmakit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.activity.MainActivity
import cu.jesusd0897.farmakit.adapter.NaturalProductAdapter
import cu.jesusd0897.farmakit.adapter.OnInteractionListener
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.viewmodel.NaturalProductViewModel
import me.zhanghai.android.fastscroll.FastScrollerBuilder

class NaturalProductsFragment :
    SearchRecyclerFragment<MinNaturalProduct>(
        R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp
    ),
    OnInteractionListener<MinNaturalProduct> {

    companion object {
        fun newInstance(): NaturalProductsFragment = NaturalProductsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val viewModel = NaturalProductViewModel(activity!!.application)
        viewModel.all.observe(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = NaturalProductAdapter(this, R.layout.item_natural_product)
        recyclerView.adapter = adapter

        FastScrollerBuilder(recyclerView)
            .useMd2Style()
            .setTrackDrawable(resources.getDrawable(R.drawable.ic_transparent))
            .build()
        return view
    }

    override fun onChanged(items: MutableList<MinNaturalProduct>) {
        super.onChanged(items)
        refreshLayout.isRefreshing = false
        (activity!! as MainActivity).setProductTabCount(1, R.string.natural_products, items.size)
    }

    override fun onClick(item: MinNaturalProduct) =
        KNav.navToNaturalProductDetail(context!!, item.id)

    override fun onLongClick(item: MinNaturalProduct) =
        Toast.makeText(context, "OnLongClick: $item", Toast.LENGTH_SHORT).show()

}