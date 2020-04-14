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
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.OnInteractionListener
import cu.jesusd0897.farmakit.adapter.RiskAdapter
import cu.jesusd0897.farmakit.model.RISKS
import cu.jesusd0897.farmakit.model.Risk
import cu.jesusd0897.farmakit.util.KNav

class RisksFragment :
    RecyclerFragment<Risk>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<Risk> {

    companion object {
        fun newInstance(): RisksFragment = RisksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = RiskAdapter(this, R.layout.item_tool)
        recyclerView.adapter = adapter
        adapter.setItems(RISKS.toMutableList())
        return view
    }

    override fun onClick(item: Risk) = KNav.navToRiskDetail(context!!, item)

    override fun onLongClick(item: Risk) {}

}