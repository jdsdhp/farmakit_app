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
import androidx.fragment.app.Fragment
import com.vansuita.materialabout.builder.AboutBuilder
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KUtil

class AboutFragment : Fragment() {

    companion object {
        fun newInstance(): AboutFragment = AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = AboutBuilder.with(context)
        .setWrapScrollView(true)
        .setLinksAnimated(true)
        .setShowAsCard(false)
        .setPhoto(R.drawable.jesusd0897)
        .setBackgroundColor(KUtil.getThemeColor(context!!, android.R.attr.colorBackground))
        .setName(R.string.app_dev)
        .setSubTitle(R.string.app_copyright)
        .setBrief(R.string.about_brief)
        .setAppIcon(R.drawable.ic_launcher_round)
        .setAppName(R.string.app_name)
        .setVersionNameAsAppSubTitle()
        .addGitHubLink("jdsdhp/farmakit_app")
        .addEmailLink(getString(R.string.app_dev_email))
        .addChangeLogAction { KNav.navToChangelog(context!!) }
        .addFeedbackAction(
            getString(R.string.app_dev_email),
            getString(R.string.app_name) + "/feedback"
        )
        //.addFiveStarsAction()
        //.addShareAction(R.string.app_name)
        .build()

}