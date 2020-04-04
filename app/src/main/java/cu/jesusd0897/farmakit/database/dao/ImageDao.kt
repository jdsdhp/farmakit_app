package cu.jesusd0897.farmakit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cu.jesusd0897.farmakit.database.ImagesColumns.TYPE
import cu.jesusd0897.farmakit.database.ImagesColumns.TYPE_ID
import cu.jesusd0897.farmakit.database.Tables.IMAGES
import cu.jesusd0897.farmakit.database.model.Image

@Dao
interface ImageDao {

    @Query("SELECT * FROM $IMAGES WHERE $TYPE_ID LIKE :typeId AND $TYPE LIKE :type")
    fun get(typeId: String, type: String): LiveData<Image>

}