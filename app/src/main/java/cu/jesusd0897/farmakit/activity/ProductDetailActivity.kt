package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.ProductPagerAdapter
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.database.repo.ProductRepo
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KUtil
import cu.jesusd0897.farmakit.viewmodel.ProductViewModel
import java.util.*
import kotlin.math.abs

class ProductDetailActivity : BaseActivity() {

    private lateinit var product: Product
    private lateinit var pagerAdapterAdapter: ProductPagerAdapter

    private lateinit var toolbar: Toolbar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var titleView: AppCompatTextView
    private lateinit var subtitleView: AppCompatTextView
    private lateinit var iconView: AppCompatImageView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val id = intent.getIntExtra(KNav.EXTRA_ID_TAG, -1)
        val viewModel = ProductViewModel(application)
        viewModel.get(id.toString()).observe(this, Observer { p ->
            product = p
            setData()
        })
        initViews()
    }

    private fun initViews() {
        titleView = findViewById(R.id.title_view)
        subtitleView = findViewById(R.id.subtitle_view)
        iconView = findViewById(R.id.icon_view)
        fab = findViewById(R.id.fab)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        addTabs(10)
        tabLayout.getTabAt(0)!!.text = getString(R.string.characteristics)
        tabLayout.getTabAt(1)!!.text = getString(R.string.contraindications)
        tabLayout.getTabAt(2)!!.text = getString(R.string.description)
        tabLayout.getTabAt(3)!!.text = getString(R.string.pharmacodinamic)
        tabLayout.getTabAt(4)!!.text = getString(R.string.indications)
        tabLayout.getTabAt(5)!!.text = getString(R.string.info)
        tabLayout.getTabAt(6)!!.text = getString(R.string.interactions)
        tabLayout.getTabAt(7)!!.text = getString(R.string.posology)
        tabLayout.getTabAt(8)!!.text = getString(R.string.precautions)
        tabLayout.getTabAt(9)!!.text = getString(R.string.reactions)
        tabLayout.getTabAt(10)!!.text = getString(R.string.overdose)

        appBarLayout = findViewById(R.id.app_bar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange > -100) {
                toolbar.title = product.name.toUpperCase(Locale.getDefault())
                toolbar.subtitle =
                    product.presentation + if (TextUtils.isEmpty(product.presentationAmount)) "" else " " + product.presentationAmount
            } else {
                title = ""
                toolbar.subtitle = ""
            }
        })
    }

    private fun addTabs(count: Int) {
        for (i in 0..count) tabLayout.addTab(tabLayout.newTab())
    }

    private fun setData() {
        titleView.text = product.name
        subtitleView.text =
            product.presentation + if (TextUtils.isEmpty(product.presentationAmount)) "" else " " + product.presentationAmount
        iconView.setImageResource(KUtil.getPresentationIcon(product.presentation))
        fab.setImageResource(if (product.isFavorite) R.drawable.ic_heart else R.drawable.ic_heart_empty)
        fab.setOnClickListener {
            product.isFavorite = !product.isFavorite
            ProductRepo(this).update(product)
        }

        pagerAdapterAdapter =
            ProductPagerAdapter(supportFragmentManager, tabLayout.tabCount, product)
        viewPager.adapter = pagerAdapterAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //tab.icon!!.alpha = 255
                val position = tab.position
                viewPager.currentItem = position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //tab.icon!!.alpha = 180
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}