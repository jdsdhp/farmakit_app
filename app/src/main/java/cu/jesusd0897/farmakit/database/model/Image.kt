package cu.jesusd0897.farmakit.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cu.jesusd0897.farmakit.database.ImagesColumns.ID
import cu.jesusd0897.farmakit.database.ImagesColumns.NAME
import cu.jesusd0897.farmakit.database.ImagesColumns.TYPE
import cu.jesusd0897.farmakit.database.ImagesColumns.TYPE_ID
import cu.jesusd0897.farmakit.database.Tables
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Tables.IMAGES)
@Parcelize
data class Image(
    @PrimaryKey @field:ColumnInfo(name = ID) val id: Int,
    @field:ColumnInfo(name = TYPE_ID) var typeId: Int,
    @field:ColumnInfo(name = TYPE) var type: String,
    @field:ColumnInfo(name = NAME) var name: String
) : Parcelable