package cu.jesusd0897.farmakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.dao.ProductDao
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct
import cu.jesusd0897.farmakit.database.repo.ProductRepo

class ProductViewModel(app: Application) : AndroidViewModel(app), ProductDao {
    private val repo: ProductRepo = ProductRepo(app)
    override val all: LiveData<MutableList<MinProduct>> = repo.all

    override fun filterByPopulation(
        old: String, hepatic: String, renal: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        repo.filterByPopulation(old, hepatic, renal, pregnancy, lactation, child)

    override fun filterByPopulationExclusive(
        old: String, hepatic: String, renal: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        repo.filterByPopulationExclusive(old, hepatic, renal, pregnancy, lactation, child)

    override fun findByCode(code: String): LiveData<Product> = repo.findByCode(code)
    override fun get(id: String): LiveData<Product> = repo.get(id)
    override fun insert(model: Product) = repo.insert(model)
    override fun insert(vararg models: Product) = repo.insert(*models)
    override fun insert(models: List<Product>) = repo.insert(models)
    override fun update(model: Product) = repo.update(model)
    override fun update(vararg models: Product) = repo.update(*models)
    override fun update(models: List<Product>) = repo.update(models)
    override fun delete(model: Product) = repo.delete(model)
    override fun delete(vararg models: Product) = repo.delete(*models)
    override fun delete(models: List<Product>) = repo.delete(models)
    override fun deleteAll() = repo.deleteAll()
}