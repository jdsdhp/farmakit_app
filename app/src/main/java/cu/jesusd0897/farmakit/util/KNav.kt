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

package cu.jesusd0897.farmakit.util

import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cu.jesusd0897.farmakit.BuildConfig
import cu.jesusd0897.farmakit.activity.*
import cu.jesusd0897.farmakit.model.Risk
import cu.jesusd0897.farmakit.model.Tool

object KNav {

    const val EXTRA_ID_TAG = "${BuildConfig.APPLICATION_ID}.extra_id"
    const val EXTRA_ITEM_TAG = "${BuildConfig.APPLICATION_ID}.extra_item"

    fun replaceFragment(
        fragmentManager: FragmentManager, fragment: Fragment, @IdRes containerViewId: Int
    ) = fragmentManager.beginTransaction().replace(containerViewId, fragment).commit()

    fun navToLibraries(context: Context) =
        context.startActivity(Intent(context, LibrariesActivity::class.java))

    fun navToChangelog(context: Context) =
        context.startActivity(Intent(context, ChangelogActivity::class.java))

    fun navToSettings(context: Context) =
        context.startActivity(Intent(context, SettingsActivity::class.java))

    fun navToAbout(context: Context) =
        context.startActivity(Intent(context, AboutActivity::class.java))

    fun navToNaturalProductDetail(context: Context, id: Int) =
        context.startActivity(
            Intent(context, NaturalProductDetailActivity::class.java).putExtra(EXTRA_ID_TAG, id)
        )

    fun navToProductDetail(context: Context, id: Int) =
        context.startActivity(
            Intent(context, ProductDetailActivity::class.java).putExtra(EXTRA_ID_TAG, id)
        )

    fun navToScanner(context: Context) =
        context.startActivity(Intent(context, ScannerActivity::class.java))

    fun navToTools(context: Context) =
        context.startActivity(Intent(context, ToolsActivity::class.java))

    fun navToToolDetail(context: Context, tool: Tool) =
        context.startActivity(
            Intent(context, ToolDetailActivity::class.java).putExtra(EXTRA_ITEM_TAG, tool)
        )

    fun navToRisks(context: Context) =
        context.startActivity(Intent(context, RisksActivity::class.java))

    fun navToRiskDetail(context: Context, risk: Risk) =
        context.startActivity(
            Intent(context, RiskDetailActivity::class.java).putExtra(EXTRA_ITEM_TAG, risk)
        )

    fun navToMain(context: Context) =
        context.startActivity(Intent(context, MainActivity::class.java))
}
