package cu.jesusd0897.farmakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.dao.ImageDao
import cu.jesusd0897.farmakit.database.model.Image
import cu.jesusd0897.farmakit.database.repo.ImageRepo

class ImageViewModel(app: Application) : AndroidViewModel(app), ImageDao {
    private val repo: ImageRepo = ImageRepo(app)
    override fun get(typeId: String, type: String): LiveData<Image> = repo.get(typeId, type)
}