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
data class Risk(
    @StringRes val name: Int,
    @StringRes val subtitle: Int,
    @StringRes val description: Int,
    @DrawableRes val icon: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val RISKS: List<Risk> = arrayListOf(
    Risk(
        R.string.risk_e,
        R.string.risk_e_subtitle,
        R.string.risk_e_description,
        R.drawable.ic_pregnant
    ),
    Risk(
        R.string.risk_lm,
        R.string.risk_lm_subtitle,
        R.string.risk_lm_description,
        R.drawable.ic_lactation
    )
)