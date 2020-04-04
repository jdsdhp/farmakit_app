package cu.jesusd0897.farmakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.dao.NaturalProductDao
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct
import cu.jesusd0897.farmakit.database.repo.NaturalProductRepo

class NaturalProductViewModel(app: Application) : AndroidViewModel(app), NaturalProductDao {
    private val repo: NaturalProductRepo = NaturalProductRepo(app)
    override val all: LiveData<MutableList<MinNaturalProduct>> = repo.all
    override fun get(id: String): LiveData<NaturalProduct> = repo.get(id)
    override fun update(model: NaturalProduct) = repo.update(model)
}