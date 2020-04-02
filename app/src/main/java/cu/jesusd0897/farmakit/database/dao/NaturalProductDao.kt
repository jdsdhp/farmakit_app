package cu.jesusd0897.farmakit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.CATEGORY
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.ID
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.IS_FAVORITE
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.NAME
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.SCIENTIFIC_NAME
import cu.jesusd0897.farmakit.database.Tables.NATURAL_PRODUCTS
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct

@Dao
interface NaturalProductDao : BasicDao<NaturalProduct> {

    @Query("DELETE FROM $NATURAL_PRODUCTS")
    fun deleteAll()

    @Query("SELECT * FROM $NATURAL_PRODUCTS WHERE $ID LIKE :id")
    fun get(id: String): LiveData<NaturalProduct>

    @get:Query(
        "SELECT $ID, $NAME, $SCIENTIFIC_NAME, $CATEGORY, $IS_FAVORITE " +
                "FROM $NATURAL_PRODUCTS ORDER BY $IS_FAVORITE DESC, $NAME"
    )
    val all: LiveData<MutableList<MinNaturalProduct>>

}