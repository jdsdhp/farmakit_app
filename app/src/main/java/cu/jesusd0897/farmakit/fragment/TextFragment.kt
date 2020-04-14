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
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import cu.jesusd0897.farmakit.R

private const val ARG_TEXT_PARAM = "text_param"

class TextFragment : Fragment() {

    private lateinit var textView: AppCompatTextView
    private var text: String? = null

    companion object {
        fun newInstance(text: String?): TextFragment {
            val fragment = TextFragment()
            val args = Bundle()
            args.putString(ARG_TEXT_PARAM, text)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) text = arguments!!.getString(ARG_TEXT_PARAM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_text, container, false)
        textView = view.findViewById(R.id.text)
        val placeholder = view.findViewById<View>(R.id.placeholder)
        if (!TextUtils.isEmpty(text)) {
            textView.visibility = View.VISIBLE
            textView.text = text
            placeholder.visibility = View.GONE
        } else {
            textView.visibility = View.GONE
            placeholder.visibility = View.VISIBLE
        }
        return view
    }

}