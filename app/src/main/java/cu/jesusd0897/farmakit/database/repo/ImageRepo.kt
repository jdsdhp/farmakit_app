package cu.jesusd0897.farmakit.database.repo

import android.content.Context
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.KRoomDatabase
import cu.jesusd0897.farmakit.database.dao.ImageDao
import cu.jesusd0897.farmakit.database.model.Image

class ImageRepo(context: Context) : ImageDao {
    private val dao: ImageDao = KRoomDatabase.getInstance(context)!!.imageDao()
    override fun get(typeId: String, type: String): LiveData<Image> = dao.get(typeId, type)
}