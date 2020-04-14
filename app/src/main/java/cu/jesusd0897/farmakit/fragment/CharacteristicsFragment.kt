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
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.database.model.Product

private const val ARG_ITEM_PARAM = "item_param"

class CharacteristicsFragment : Fragment() {

    private lateinit var product: Product

    companion object {
        fun newInstance(product: Product): CharacteristicsFragment {
            val fragment = CharacteristicsFragment()
            val args = Bundle()
            args.putParcelable(ARG_ITEM_PARAM, product)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) product = arguments!!.getParcelable(ARG_ITEM_PARAM)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.fragment_product_caracteristics, container, false)
        setData(view)
        return view
    }

    private fun setData(view: View) {
        view.findViewById<AppCompatTextView>(R.id.product_categories).text =
            if (product.category != null) product.category else getString(R.string.no_category)
        view.findViewById<AppCompatTextView>(R.id.product_classification_ven).text =
            if (product.classification != null) product.classification else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_composition).text =
            if (product.composition != null) product.composition else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_internal_name).text =
            if (product.internalName != null) product.internalName else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_laboratory).text =
            if (product.laboratory != null) product.laboratory else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_oms).text =
            when {
                product.isOmsProduct() == null -> getString(R.string.not_available)
                product.isOmsProduct()!! -> getString(R.string.yes)
                else -> getString(R.string.no)
            }
        view.findViewById<AppCompatTextView>(R.id.product_distribution).text =
            if (product.distribution != null) product.distribution else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_price).text =
            if (product.price != null) product.price else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_regulation).text =
            if (product.regulation != null) product.regulation else getString(R.string.not_available)
        view.findViewById<AppCompatTextView>(R.id.product_vigilance).text = when {
            product.hasIntensiveVigilance() == null -> getString(R.string.not_available)
            product.hasIntensiveVigilance()!! -> getString(R.string.yes)
            else -> getString(R.string.no)
        }
        product.vigilance.toString()
        view.findViewById<AppCompatTextView>(R.id.product_population).text =
            if (product.population != null) product.population else getString(R.string.not_available)
    }

}