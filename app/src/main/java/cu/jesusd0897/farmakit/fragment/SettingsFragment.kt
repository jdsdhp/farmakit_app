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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.activity.SettingsActivity
import cu.jesusd0897.farmakit.config.KSettings
import cu.jesusd0897.farmakit.util.KNav

class SettingsFragment : Fragment() {

    //private lateinit var langCardView: CardView
    private lateinit var langTextView: AppCompatTextView
    private lateinit var darkThemeSwitch: SwitchCompat

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        initViews(view)
        initActionListeners(view)
        return view
    }

    private fun initViews(view: View) {
        /*langTextView = view.findViewById(R.id.setting_value_lang)
        val languages = resources.getStringArray(R.array.language_values)
        val currentLang = KSettings.getLang(context!!)
        langTextView.text =
            if (ES_LANG == currentLang) languages[0] else languages[1]
        langCardView = view.findViewById(R.id.setting_lang_layout)
        langCardView.setOnClickListener {
            KAlert.buildChoiceDialog(
                context!!, R.string.language_select,
                null, null, R.string.cancel, null, true,
                null, null, R.array.language_values,
                if (currentLang == ES_LANG) 0 else 1,
                DialogInterface.OnClickListener { _, which ->
                    KSettings.setLang(context!!, if (which == 0) ES_LANG else EN_LANG)
                    langTextView.text = languages[which]
                    restartActivity()
                }
            ).show()
        }*/

        darkThemeSwitch = view.findViewById(R.id.setting_value_theme)
        darkThemeSwitch.isChecked = KSettings.isDarkTheme(context!!)
        darkThemeSwitch.setOnCheckedChangeListener { _, _ ->
            KSettings.setDarkTheme(context!!, !KSettings.isDarkTheme(context!!))
            restartActivity()
        }
    }

    private fun initActionListeners(view: View) {
        view.findViewById<CardView>(R.id.setting_about)
            .setOnClickListener { KNav.navToAbout(context!!) }
        view.findViewById<CardView>(R.id.setting_libraries)
            .setOnClickListener { KNav.navToLibraries(context!!) }
    }

    private fun restartActivity() {
        startActivity(Intent(activity!!, SettingsActivity::class.java))
        activity!!.finish()
    }

}