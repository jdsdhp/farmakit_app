package cu.jesusd0897.farmakit.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.CATEGORY
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.DESCRIPTION
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.ID
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.IS_FAVORITE
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.NAME
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.SCIENTIFIC_NAME
import cu.jesusd0897.farmakit.database.Tables
import cu.jesusd0897.farmakit.model.Model
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Tables.NATURAL_PRODUCTS)
@Parcelize
data class NaturalProduct(
    @PrimaryKey @field:ColumnInfo(name = ID) val id: Int,
    @field:ColumnInfo(name = NAME) var name: String,
    @field:ColumnInfo(name = SCIENTIFIC_NAME) var scientificName: String,
    @field:ColumnInfo(name = CATEGORY) var category: String,
    @field:ColumnInfo(name = DESCRIPTION) var description: String,
    @field:ColumnInfo(name = IS_FAVORITE, defaultValue = "0") var isFavorite: Boolean
) : Model {

    override fun match(query: String?): Boolean =
        if (query == null) true
        else (name.contains(query, true)
                || scientificName.contains(query, true)
                || category.contains(query, true))

}