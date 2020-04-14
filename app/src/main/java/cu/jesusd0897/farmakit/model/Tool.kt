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

package cu.jesusd0897.farmakit.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import cu.jesusd0897.farmakit.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tool(
    @StringRes val name: Int,
    @StringRes val subtitle: Int,
    @StringRes val description: Int,
    @DrawableRes val icon: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val TOOLS: List<Tool> = arrayListOf(
    Tool(
        R.string.tool_dripping_ev,
        R.string.tool_dripping_ev_subtitle,
        R.string.tool_dripping_ev_description,
        R.drawable.ic_tint
    ),
    Tool(
        R.string.tool_quetelet,
        R.string.tool_quetelet_subtitle,
        R.string.tool_quetelet_description,
        R.drawable.ic_analytics
    )
)