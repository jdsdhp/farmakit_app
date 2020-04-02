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