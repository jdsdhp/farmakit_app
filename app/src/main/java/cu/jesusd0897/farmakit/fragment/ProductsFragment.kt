package cu.jesusd0897.farmakit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntRange
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.activity.MainActivity
import cu.jesusd0897.farmakit.adapter.OnInteractionListener
import cu.jesusd0897.farmakit.adapter.ProductAdapter
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.viewmodel.ProductViewModel
import me.zhanghai.android.fastscroll.FastScrollerBuilder

class ProductsFragment :
    SearchRecyclerFragment<MinProduct>(
        R.string.no_items_found, R.drawable.ic_find_in_page_black_24dp
    ), OnInteractionListener<MinProduct> {

    private lateinit var viewModel: ProductViewModel
    private val DB_FLAGS = arrayOf(
        "%Adolescente:%", "%Adulto mayor:%", "%Deficiencia hepática:%", "%Deficiencia renal:%",
        "%Diabetes Mellitus:%", "%Embarazo:%", "%Lactancia:%", "%Niño:%"
    )

    companion object {
        fun newInstance(): ProductsFragment = ProductsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ProductViewModel(activity!!.application)
        viewModel.all.observe(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        adapter = ProductAdapter(this, R.layout.item_product)
        recyclerView.adapter = adapter

        FastScrollerBuilder(recyclerView)
            .useMd2Style()
            .setTrackDrawable(resources.getDrawable(R.drawable.ic_transparent))
            .build()
        return view
    }

    override fun onChanged(items: MutableList<MinProduct>) {
        super.onChanged(items)
        refreshLayout.isRefreshing = false
        (activity!! as MainActivity).setProductTabCount(0, R.string.products, items.size)
    }

    override fun onClick(item: MinProduct) = KNav.navToProductDetail(context!!, item.id)

    override fun onLongClick(item: MinProduct) =
        Toast.makeText(context, "OnLongClick: $item", Toast.LENGTH_SHORT).show()

    fun changeFilter(@IntRange(from = 0, to = 1) radioIndexChecked: Int, booleans: BooleanArray?) {
        viewModel = ProductViewModel(activity!!.application)
        var flags = booleans
        if (flags == null) flags = booleanArrayOf(true, true, true, true, true, true, true, true)

        if (!flags.contains(false)) {
            if (radioIndexChecked == 0) viewModel.all.observe(this, this)
            else viewModel.filterByRisksExclusive(
                DB_FLAGS[0], DB_FLAGS[1], DB_FLAGS[2], DB_FLAGS[3],
                DB_FLAGS[4], DB_FLAGS[5], DB_FLAGS[6], DB_FLAGS[7]
            ).observe(this, this)
        } else {
            if (radioIndexChecked == 0) viewModel.filterByRisksInclusive(
                if (flags[0]) DB_FLAGS[0] else "NULL VALUE",
                if (flags[1]) DB_FLAGS[1] else "NULL VALUE",
                if (flags[2]) DB_FLAGS[2] else "NULL VALUE",
                if (flags[3]) DB_FLAGS[3] else "NULL VALUE",
                if (flags[4]) DB_FLAGS[4] else "NULL VALUE",
                if (flags[5]) DB_FLAGS[5] else "NULL VALUE",
                if (flags[6]) DB_FLAGS[6] else "NULL VALUE",
                if (flags[7]) DB_FLAGS[7] else "NULL VALUE"
            ).observe(this, this)
            else viewModel.filterByRisksExclusive(
                if (flags[0]) DB_FLAGS[0] else "%%",
                if (flags[1]) DB_FLAGS[1] else "%%",
                if (flags[2]) DB_FLAGS[2] else "%%",
                if (flags[3]) DB_FLAGS[3] else "%%",
                if (flags[4]) DB_FLAGS[4] else "%%",
                if (flags[5]) DB_FLAGS[5] else "%%",
                if (flags[6]) DB_FLAGS[6] else "%%",
                if (flags[7]) DB_FLAGS[7] else "%%"
            ).observe(this, this)
        }


    }

}