package cu.jesusd0897.farmakit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cu.jesusd0897.farmakit.database.ProductsColumns.CATEGORY
import cu.jesusd0897.farmakit.database.ProductsColumns.CODE
import cu.jesusd0897.farmakit.database.ProductsColumns.ID
import cu.jesusd0897.farmakit.database.ProductsColumns.INTERNAL_NAME
import cu.jesusd0897.farmakit.database.ProductsColumns.IS_FAVORITE
import cu.jesusd0897.farmakit.database.ProductsColumns.LABORATORY
import cu.jesusd0897.farmakit.database.ProductsColumns.NAME
import cu.jesusd0897.farmakit.database.ProductsColumns.POPULATION
import cu.jesusd0897.farmakit.database.ProductsColumns.PRECAUTIONS
import cu.jesusd0897.farmakit.database.ProductsColumns.PRESENTATION
import cu.jesusd0897.farmakit.database.ProductsColumns.PRESENTATION_AMOUNT
import cu.jesusd0897.farmakit.database.ProductsColumns.PRICE
import cu.jesusd0897.farmakit.database.Tables.PRODUCTS
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct

@Dao
interface ProductDao : BasicDao<Product> {

    @Query("DELETE FROM $PRODUCTS")
    fun deleteAll()

    @Query("SELECT * FROM $PRODUCTS WHERE $ID LIKE :id")
    fun get(id: String): LiveData<Product>

    @get:Query(
        "SELECT $ID, $NAME, $PRESENTATION, $PRESENTATION_AMOUNT, $INTERNAL_NAME, $CATEGORY, $LABORATORY, $PRICE, $IS_FAVORITE " +
                "FROM $PRODUCTS ORDER BY $IS_FAVORITE DESC, $NAME"
    )
    val all: LiveData<MutableList<MinProduct>>

    @Query(
        "SELECT DISTINCT $ID, $NAME, $PRESENTATION, $PRESENTATION_AMOUNT, $INTERNAL_NAME, $CATEGORY, $LABORATORY, $PRICE, $IS_FAVORITE " +
                "FROM $PRODUCTS " +
                "WHERE (" + POPULATION + " LIKE :pregnancy OR " + PRECAUTIONS + " LIKE :pregnancy) OR " +
                "(" + POPULATION + " LIKE :lactation OR " + PRECAUTIONS + " LIKE :lactation) OR " +
                "(" + POPULATION + " LIKE :hepatic OR " + PRECAUTIONS + " LIKE :hepatic) OR " +
                "(" + POPULATION + " LIKE :renal OR " + PRECAUTIONS + " LIKE :renal) OR " +
                "(" + POPULATION + " LIKE :old OR " + PRECAUTIONS + " LIKE :old) OR " +
                "(" + POPULATION + " LIKE :child OR " + PRECAUTIONS + " LIKE :child) " +
                "ORDER BY $IS_FAVORITE DESC, $NAME"
    )
    fun filterByPopulation(
        old: String,
        hepatic: String,
        renal: String,
        pregnancy: String,
        lactation: String,
        child: String
    ): LiveData<MutableList<MinProduct>>

    @Query(
        "SELECT DISTINCT $ID, $NAME, $PRESENTATION, $PRESENTATION_AMOUNT, $INTERNAL_NAME, $CATEGORY, $LABORATORY, $PRICE, $IS_FAVORITE " +
                "FROM $PRODUCTS " +
                "WHERE (" + POPULATION + " LIKE :pregnancy OR " + PRECAUTIONS + " LIKE :pregnancy) AND " +
                "(" + POPULATION + " LIKE :lactation OR " + PRECAUTIONS + " LIKE :lactation) AND " +
                "(" + POPULATION + " LIKE :hepatic OR " + PRECAUTIONS + " LIKE :hepatic) AND " +
                "(" + POPULATION + " LIKE :renal OR " + PRECAUTIONS + " LIKE :renal) AND " +
                "(" + POPULATION + " LIKE :old OR " + PRECAUTIONS + " LIKE :old) AND " +
                "(" + POPULATION + " LIKE :child OR " + PRECAUTIONS + " LIKE :child) " +
                "ORDER BY $IS_FAVORITE DESC, $NAME"
    )
    fun filterByPopulationExclusive(
        old: String,
        hepatic: String,
        renal: String,
        pregnancy: String,
        lactation: String,
        child: String
    ): LiveData<MutableList<MinProduct>>

    @Query("SELECT * FROM $PRODUCTS WHERE $CODE LIKE :code")
    fun findByCode(code: String): LiveData<Product>

}