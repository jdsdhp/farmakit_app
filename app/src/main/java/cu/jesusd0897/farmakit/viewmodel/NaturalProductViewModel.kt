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
    override fun insert(model: NaturalProduct) = repo.insert(model)
    override fun insert(vararg models: NaturalProduct) = repo.insert(*models)
    override fun insert(models: List<NaturalProduct>) = repo.insert(models)
    override fun update(model: NaturalProduct) = repo.update(model)
    override fun update(vararg models: NaturalProduct) = repo.update(*models)
    override fun update(models: List<NaturalProduct>) = repo.update(models)
    override fun delete(model: NaturalProduct) = repo.delete(model)
    override fun delete(vararg models: NaturalProduct) = repo.delete(*models)
    override fun delete(models: List<NaturalProduct>) = repo.delete(models)
    override fun deleteAll() = repo.deleteAll()
}