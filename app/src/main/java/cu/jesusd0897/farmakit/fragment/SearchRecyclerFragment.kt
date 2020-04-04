package cu.jesusd0897.farmakit.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.appbar.AppBarLayout
import com.miguelcatalan.materialsearchview.MaterialSearchView
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.activity.MainActivity
import cu.jesusd0897.farmakit.model.Model
import java.util.*

abstract class SearchRecyclerFragment<M : Model?> internal constructor(
    @field:StringRes @param:StringRes private val emptyTitle: Int,
    @field:DrawableRes @param:DrawableRes private val emptyIcon: Int
) : RecyclerFragment<M>(emptyTitle, emptyIcon),
    MaterialSearchView.OnQueryTextListener,
    MaterialSearchView.SearchViewListener {

    private lateinit var searchView: MaterialSearchView
    private lateinit var appBarLayout: AppBarLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        searchView = activity!!.findViewById(R.id.search_view)
        appBarLayout = activity!!.findViewById(R.id.app_bar)
        searchView.setOnSearchViewListener(this)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchItem = menu.findItem(R.id.action_search)
        searchView.setMenuItem(searchItem)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        searchView.closeSearch()
        adapter.setItems(items)
        if (this.items.isEmpty()) setEmptyPlaceholder()
        else emptyPlaceholder.visibility = View.GONE
    }

    override fun onChanged(items: MutableList<M>) {
        this.items = items
        adapter.setItems(this.items)
        if (this.items.isEmpty()) setEmptyPlaceholder()
        else emptyPlaceholder.visibility = View.GONE
    }

    override fun onQueryTextSubmit(query: String): Boolean = onQueryTextChange(query)

    override fun onQueryTextChange(newText: String): Boolean {
        if (TextUtils.isEmpty(newText)) {
            emptyPlaceholder.visibility = View.GONE
            if (items.isNotEmpty()) adapter.setItems(items) else setEmptyPlaceholder()
        } else {
            val newList: MutableList<M> = ArrayList()
            for (model in items) if (model!!.match(newText)) newList.add(model)
            adapter.setItems(newList)
            when {
                items.isEmpty() -> setEmptyPlaceholder()
                newList.isEmpty() -> setEmptySearchPlaceholder()
                else -> emptyPlaceholder.visibility = View.GONE
            }
        }
        return true
    }

    private fun setEmptySearchPlaceholder() {
        emptyPlaceholderTile.setText(R.string.we_sorry)
        emptyPlaceholderSubtitle.setText(R.string.no_items_found)
        emptyPlaceholderImage.setImageResource(R.drawable.ic_find_in_page_black_24dp)
        emptyPlaceholder.visibility = View.VISIBLE
    }

    override fun onSearchViewShown() {
        appBarLayout.setExpanded(false)
        (activity!! as MainActivity).hideFab()
    }

    override fun onSearchViewClosed() {
        appBarLayout.setExpanded(true)
        if (items.isEmpty()) setEmptyPlaceholder() else adapter.setItems(items)
        (activity!! as MainActivity).showFab()
    }
}