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
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.util.KAlert
import cu.jesusd0897.farmakit.util.KUtil
import kotlin.math.pow

class ImcFragment : Fragment() {

    private lateinit var weightLayout: TextInputLayout
    private lateinit var heightLayout: TextInputLayout

    companion object {
        fun newInstance(): ImcFragment = ImcFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_imc, container, false)
        weightLayout = view.findViewById(R.id.weight_layout)
        heightLayout = view.findViewById(R.id.height_layout)
        view.findViewById<AppCompatButton>(R.id.calculate_btn)
            .setOnClickListener { if (isValid()) showResults() }
        return view
    }

    private fun showResults() {
        val result = calculate(
            weightLayout.editText!!.text.toString().toDouble(),
            heightLayout.editText!!.text.toString().toDouble()
        )
        val pair = KUtil.getResultFlag(result)

        val view = layoutInflater.inflate(R.layout.content_imc_result_layout, null)
        val imcResultView = view.findViewById<AppCompatTextView>(R.id.result_title)
        val resultSubtitleView = view.findViewById<AppCompatTextView>(R.id.result_subtitle)
        val resultSubtitleView2 = view.findViewById<AppCompatTextView>(R.id.imc_result_subtitle2)
        imcResultView.text = "${KUtil.getTwoDecimals(result)} kg/m²"
        resultSubtitleView.text = getString(pair.first)
        resultSubtitleView2.text = getString(pair.second)

        KAlert.buildSimpleOKDialog(
                context!!, getString(R.string.result),
                null, getString(R.string.accept), null, true
            )
            .setView(view)
            .show()
    }

    private fun isValid(): Boolean {
        var isValid = true
        weightLayout.error = null
        heightLayout.error = null

        if (TextUtils.isEmpty(weightLayout.editText!!.text.toString())) {
            weightLayout.error = getString(R.string.required_field)
            isValid = false
        }
        if (TextUtils.isEmpty(heightLayout.editText!!.text.toString())) {
            heightLayout.error = getString(R.string.required_field)
            isValid = false
        }
        return isValid
    }

    private fun calculate(weight: Double, height: Double): Double = weight / ((height / 100).pow(2))

}