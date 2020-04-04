package cu.jesusd0897.farmakit.model

import androidx.annotation.StringRes
import cu.jesusd0897.farmakit.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChangeLog(
    @StringRes val version: Int,
    @StringRes val date: Int,
    @StringRes val changes: Int
) : Model {
    override fun match(query: String?): Boolean = true
}

val CHANGE_LOGS: List<ChangeLog> = arrayListOf(
    ChangeLog(
        R.string.change_log_060_version,
        R.string.change_log_060_date,
        R.string.change_log_060_changes
    ),
    ChangeLog(
        R.string.change_log_050_version,
        R.string.change_log_050_date,
        R.string.change_log_050_changes
    )
)