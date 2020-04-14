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
import kotlin.math.roundToInt

class DrippingFragment : Fragment() {

    private lateinit var volLayout: TextInputLayout
    private lateinit var durationLayout: TextInputLayout

    companion object {
        fun newInstance(): DrippingFragment = DrippingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_dripping, container, false)
        volLayout = view.findViewById(R.id.vol_layout)
        durationLayout = view.findViewById(R.id.duration_layout)
        view.findViewById<AppCompatButton>(R.id.calculate_btn)
            .setOnClickListener { if (isValid()) showResults() }
        return view
    }

    private fun showResults() {
        val result = calculate(
            volLayout.editText!!.text.toString().toDouble(),
            durationLayout.editText!!.text.toString().toDouble()
        )

        val view = layoutInflater.inflate(R.layout.content_dripping_result_layout, null)
        val resultView = view.findViewById<AppCompatTextView>(R.id.result_title)
        resultView.text = "$result gotas/min"

        KAlert.buildSimpleOKDialog(
                context!!, getString(R.string.result),
                null, getString(R.string.accept), null, true
            )
            .setView(view)
            .show()
    }

    private fun isValid(): Boolean {
        var isValid = true
        volLayout.error = null
        durationLayout.error = null

        if (TextUtils.isEmpty(volLayout.editText!!.text.toString())) {
            volLayout.error = getString(R.string.required_field)
            isValid = false
        } else if (volLayout.editText!!.text.toString().toDouble() == 0.toDouble()) {
            volLayout.error = getString(R.string.value_cant_be_0)
            isValid = false
        }
        if (TextUtils.isEmpty(durationLayout.editText!!.text.toString())) {
            durationLayout.error = getString(R.string.required_field)
            isValid = false
        } else if (durationLayout.editText!!.text.toString().toDouble() == 0.toDouble()) {
            durationLayout.error = getString(R.string.value_cant_be_0)
            isValid = false
        }
        return isValid
    }

    private fun calculate(vol: Double, duration: Double): Int =
        ((vol * 20) / (duration * 60)).roundToInt()

}