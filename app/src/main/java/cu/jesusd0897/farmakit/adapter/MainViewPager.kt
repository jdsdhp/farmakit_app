package cu.jesusd0897.farmakit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import cu.jesusd0897.farmakit.fragment.NaturalProductsFragment
import cu.jesusd0897.farmakit.fragment.ProductsFragment

class MainViewPager(fm: FragmentManager, private val tabsCount: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var productsFragment: ProductsFragment? = null
        get() = if (field == null) getItem(0) as ProductsFragment else field

    var naturalProductsFragment: NaturalProductsFragment? = null
        get() = if (field == null) getItem(1) as NaturalProductsFragment else field

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> {
                productsFragment = ProductsFragment.newInstance()
                productsFragment!!
            }
            else -> {
                naturalProductsFragment = NaturalProductsFragment.newInstance()
                naturalProductsFragment!!
            }
        }

    override fun getCount(): Int = tabsCount
}