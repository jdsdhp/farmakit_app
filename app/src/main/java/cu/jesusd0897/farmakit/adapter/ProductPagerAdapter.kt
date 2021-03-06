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
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.fragment.CharacteristicsFragment
import cu.jesusd0897.farmakit.fragment.TextFragment

class ProductPagerAdapter(
    fm: FragmentManager, private val tabsCount: Int, private val product: Product
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> CharacteristicsFragment.newInstance(product)
            1 -> TextFragment.newInstance(product.contraindications)
            2 -> TextFragment.newInstance(product.description)
            3 -> TextFragment.newInstance(product.pharmacodinamic)
            4 -> TextFragment.newInstance(product.indications)
            5 -> TextFragment.newInstance(product.info)
            6 -> TextFragment.newInstance(product.interactions)
            7 -> TextFragment.newInstance(product.posology)
            8 -> TextFragment.newInstance(product.precautions)
            9 -> TextFragment.newInstance(product.reactions)
            else -> TextFragment.newInstance(product.overdoseTreatment)
        }

    override fun getCount(): Int = tabsCount
}