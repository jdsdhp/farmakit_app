package cu.jesusd0897.farmakit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.OnInteractionListener
import cu.jesusd0897.farmakit.adapter.ToolAdapter
import cu.jesusd0897.farmakit.model.TOOLS
import cu.jesusd0897.farmakit.model.Tool
import cu.jesusd0897.farmakit.util.KNav

class ToolsFragment :
    RecyclerFragment<Tool>(R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp),
    OnInteractionListener<Tool> {

    companion object {
        fun newInstance(): ToolsFragment = ToolsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = ToolAdapter(this, R.layout.item_tool)
        recyclerView.adapter = adapter
        adapter.setItems(TOOLS.toMutableList())
        return view
    }

    override fun onClick(item: Tool) = KNav.navToToolDetail(context!!, item)

    override fun onLongClick(item: Tool) {}

}