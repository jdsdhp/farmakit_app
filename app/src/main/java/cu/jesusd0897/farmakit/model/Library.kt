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
    /*Library(
        R.string.library_circleimageview,
        R.string.library_circleimageview_link,
        R.string.library_circleimageview_license
    ),*/
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
    ), Library(
        R.string.library_okhttp3,
        R.string.library_okhttp3_link,
        R.string.library_okhttp3_license
    ),
    Library(
        R.string.library_picasso,
        R.string.library_picasso_link,
        R.string.library_picasso_license
    )/*
    Library(
        R.string.library_sqlcipher,
        R.string.library_sqlcipher_link,
        R.string.library_sqlcipher_license
    )*/
)