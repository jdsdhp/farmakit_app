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

package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.miguelcatalan.materialsearchview.MaterialSearchView
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.adapter.MainViewPager
import cu.jesusd0897.farmakit.ui.KFilterDialog
import cu.jesusd0897.farmakit.util.KNav

class MainActivity : BaseActivity(), MaterialSearchView.OnQueryTextListener,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var searchView: MaterialSearchView
    private lateinit var tabLayout: TabLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var pagerAdapter: MainViewPager
    private lateinit var filterDialog: KFilterDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        toggle.isDrawerIndicatorEnabled = false
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_navigation_menu, theme)
        toggle.setHomeAsUpIndicator(drawable)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toggle.setToolbarNavigationClickListener {
            if (drawer.isDrawerVisible(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
            else drawer.openDrawer(GravityCompat.START)
        }
        navigationView.setNavigationItemSelectedListener(this)

        searchView = findViewById(R.id.search_view)
        appBarLayout = findViewById(R.id.app_bar)
        tabLayout = findViewById(R.id.tabs)
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.getTabAt(0)!!.text = getString(R.string.products)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_flask_potion)
        tabLayout.getTabAt(1)!!.text = getString(R.string.natural_products)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_leaf)
        tabLayout.getTabAt(1)!!.icon!!.alpha = 180

        val viewPager = findViewById<ViewPager>(R.id.main_view_pager)
        pagerAdapter = MainViewPager(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon!!.alpha = 255
                val position = tab.position
                viewPager.currentItem = position
                if (searchView.isSearchOpen) searchView.closeSearch()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon!!.alpha = 180
                if (tab.position == 0) hideFab() else showFab()
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        searchView.setOnQueryTextListener(this)
        filterDialog = KFilterDialog(
            this, null, null, null, null,
            R.string.accept, R.string.cancel, R.string.reset_filter,
            object : KFilterDialog.OnClickListener {
                override fun onPositiveClick(radioIndexChecked: Int, checkedItems: BooleanArray) =
                    pagerAdapter.productsFragment!!.changeFilter(radioIndexChecked, checkedItems)

                override fun onNegativeClick() {}
                override fun onNeutralClick() =
                    pagerAdapter.productsFragment!!.changeFilter(0, null)
            }
        )
        fab = findViewById(R.id.fab)
        fab.setOnClickListener { filterDialog.show() }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val id: Int = menuItem.itemId
        Handler().postDelayed({ handleNavSelected(id) }, 250)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleNavSelected(itemId: Int) {
        when (itemId) {
            R.id.action_qr_scanner -> KNav.navToScanner(this)
            R.id.action_risks -> KNav.navToRisks(this)
            R.id.action_tools -> KNav.navToTools(this)
            R.id.action_settings -> {
                KNav.navToSettings(this)
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchView.setMenuItem(menu.findItem(R.id.action_search))
        return true
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen || drawer.isDrawerOpen(GravityCompat.START)) {
            searchView.closeSearch()
            drawer.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> hideFab()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextChange(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (tabLayout.selectedTabPosition == 0) pagerAdapter.productsFragment!!.onQueryTextChange(
            newText!!
        )
        else pagerAdapter.naturalProductsFragment!!.onQueryTextSubmit(newText!!)
        return true
    }

    fun hideFab() = fab.hide()

    fun showFab() {
        if (tabLayout.selectedTabPosition == 0) fab.show()
    }

    fun setProductTabCount(
        @IntRange(from = 0, to = 1) tabIndex: Int,
        @StringRes tabTitle: Int,
        count: Int
    ) {
        tabLayout.getTabAt(tabIndex)!!.text = getString(tabTitle) + " ($count)"
    }
}