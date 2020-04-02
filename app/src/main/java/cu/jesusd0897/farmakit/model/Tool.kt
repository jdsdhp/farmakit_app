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