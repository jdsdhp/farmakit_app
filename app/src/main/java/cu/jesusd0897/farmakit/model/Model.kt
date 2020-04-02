package cu.jesusd0897.farmakit.model

import android.os.Parcelable

interface Model : Parcelable {
    fun match(query: String?): Boolean
}