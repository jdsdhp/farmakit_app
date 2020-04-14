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

import androidx.annotation.StringRes
import cu.jesusd0897.farmakit.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Library(
    @StringRes val name: Int,
    @StringRes val link: Int,
    @StringRes val license: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val LIBRARIES: List<Library> = arrayListOf(
    Library(
        R.string.library_android_fast_scroll,
        R.string.library_android_fast_scroll_link,
        R.string.library_android_fast_scroll_license
    ),
    Library(
        R.string.library_barcodescanner,
        R.string.library_barcodescanner_link,
        R.string.library_barcodescanner_license
    ),
    Library(
        R.string.library_circleimageview,
        R.string.library_circleimageview_link,
        R.string.library_circleimageview_license
    ),
    Library(
        R.string.library_kotlin,
        R.string.library_kotlin_link,
        R.string.library_kotlin_license
    ),
    Library(
        R.string.library_lottie,
        R.string.library_lottie_link,
        R.string.library_lottie_license
    ),
    Library(
        R.string.library_material,
        R.string.library_material_link,
        R.string.library_material_license
    ),
    Library(
        R.string.library_materialabout,
        R.string.library_materialabout_link,
        R.string.library_materialabout_license
    ),
    Library(
        R.string.library_materialsearchview,
        R.string.library_materialsearchview_link,
        R.string.library_materialsearchview_license
    ),
    Library(
        R.string.library_picasso,
        R.string.library_picasso_link,
        R.string.library_picasso_license
    )
)