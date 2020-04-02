package cu.jesusd0897.farmakit.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import cu.jesusd0897.farmakit.database.ProductsColumns.CATEGORY
import cu.jesusd0897.farmakit.database.ProductsColumns.CLASSIFICATION
import cu.jesusd0897.farmakit.database.ProductsColumns.CODE
import cu.jesusd0897.farmakit.database.ProductsColumns.COMPOSITION
import cu.jesusd0897.farmakit.database.ProductsColumns.CONTRAINDICATIONS
import cu.jesusd0897.farmakit.database.ProductsColumns.DESCRIPTION
import cu.jesusd0897.farmakit.database.ProductsColumns.DISTRIBUTION
import cu.jesusd0897.farmakit.database.ProductsColumns.DOSAGE
import cu.jesusd0897.farmakit.database.ProductsColumns.ID
import cu.jesusd0897.farmakit.database.ProductsColumns.INDICATIONS
import cu.jesusd0897.farmakit.database.ProductsColumns.INFO
import cu.jesusd0897.farmakit.database.ProductsColumns.INTERACTIONS
import cu.jesusd0897.farmakit.database.ProductsColumns.INTERNAL_NAME
import cu.jesusd0897.farmakit.database.ProductsColumns.IS_FAVORITE
import cu.jesusd0897.farmakit.database.ProductsColumns.LABORATORY
import cu.jesusd0897.farmakit.database.ProductsColumns.NAME
import cu.jesusd0897.farmakit.database.ProductsColumns.OMS
import cu.jesusd0897.farmakit.database.ProductsColumns.OVERDOSE_TREATMENT
import cu.jesusd0897.farmakit.database.ProductsColumns.PHARMACODINAMIC
import cu.jesusd0897.farmakit.database.ProductsColumns.POPULATION
import cu.jesusd0897.farmakit.database.ProductsColumns.POSOLOGY
import cu.jesusd0897.farmakit.database.ProductsColumns.PRECAUTIONS
import cu.jesusd0897.farmakit.database.ProductsColumns.PRESENTATION
import cu.jesusd0897.farmakit.database.ProductsColumns.PRESENTATION_AMOUNT
import cu.jesusd0897.farmakit.database.ProductsColumns.PRICE
import cu.jesusd0897.farmakit.database.ProductsColumns.REACTIONS
import cu.jesusd0897.farmakit.database.ProductsColumns.REGULATION
import cu.jesusd0897.farmakit.database.ProductsColumns.UPDATED_AT
import cu.jesusd0897.farmakit.database.ProductsColumns.VIGILANCE
import cu.jesusd0897.farmakit.database.Tables
import cu.jesusd0897.farmakit.model.Model
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Tables.PRODUCTS/*, indices = [Index(value = [NAME])]*/)
@Parcelize
data class Product(
    @PrimaryKey @field:ColumnInfo(name = ID) val id: Int,
    @field:ColumnInfo(name = NAME) var name: String,
    @field:ColumnInfo(name = PRESENTATION) var presentation: String,
    @field:ColumnInfo(name = PRESENTATION_AMOUNT) var presentationAmount: String?,
    @field:ColumnInfo(name = CODE) var code: String?,
    @field:ColumnInfo(name = INTERNAL_NAME) var internalName: String?,
    @field:ColumnInfo(name = REGULATION) var regulation: String?,
    @field:ColumnInfo(name = DISTRIBUTION) var distribution: String?,
    @field:ColumnInfo(name = CLASSIFICATION) var classification: String?,
    @field:ColumnInfo(name = CATEGORY) var category: String?,
    @field:ColumnInfo(name = LABORATORY) var laboratory: String?,
    @field:ColumnInfo(name = PRICE) var price: String?,
    @field:ColumnInfo(name = VIGILANCE, defaultValue = "-1") var vigilance: Int,
    @field:ColumnInfo(name = OMS, defaultValue = "-1") var oms: Int,
    @field:ColumnInfo(name = INDICATIONS) var indications: String,
    @field:ColumnInfo(name = POSOLOGY) var posology: String,
    @field:ColumnInfo(name = DESCRIPTION) var description: String,
    @field:ColumnInfo(name = COMPOSITION) var composition: String?,
    @field:ColumnInfo(name = PHARMACODINAMIC) var pharmacodinamic: String?,
    @field:ColumnInfo(name = CONTRAINDICATIONS) var contraindications: String,
    @field:ColumnInfo(name = REACTIONS) var reactions: String,
    @field:ColumnInfo(name = PRECAUTIONS) var precautions: String,
    @field:ColumnInfo(name = INTERACTIONS) var interactions: String,
    @field:ColumnInfo(name = OVERDOSE_TREATMENT) var overdoseTreatment: String?,
    @field:ColumnInfo(name = INFO) var info: String,
    @field:ColumnInfo(name = POPULATION) var population: String?,
    @field:ColumnInfo(name = DOSAGE) var dosage: String?,
    @field:ColumnInfo(name = UPDATED_AT) var updatedAt: String?,
    @field:ColumnInfo(name = IS_FAVORITE, defaultValue = "0") var isFavorite: Boolean
) : Model {

    override fun match(query: String?): Boolean =
        if (query == null) true
        else (name.contains(query, true)
                || ("$presentation $presentationAmount").contains(query, true))
                || (category?.contains(query, true) ?: false)
                || (internalName?.contains(query, true) ?: false)
                || (laboratory?.contains(query, true) ?: false)
                || (price?.contains(query, true) ?: false)

    @Ignore
    fun isOmsProduct(): Boolean? = if (oms == -1) null else oms == 1

    @Ignore
    fun hasIntensiveVigilance(): Boolean? = if (vigilance == -1) null else vigilance == 1

}