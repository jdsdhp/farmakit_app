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