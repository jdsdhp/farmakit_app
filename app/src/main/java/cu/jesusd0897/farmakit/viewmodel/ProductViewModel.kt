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
    override fun findByCode(code: String): LiveData<Product> = repo.findByCode(code)
    override fun get(id: String): LiveData<Product> = repo.get(id)

    override fun filterByRisksInclusive(
        teenager: String, old: String, hepatic: String, renal: String, diabetes: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        repo.filterByRisksInclusive(
            teenager, old, hepatic, renal, diabetes, pregnancy, lactation, child
        )

    override fun filterByRisksExclusive(
        teenager: String, old: String, hepatic: String, renal: String, diabetes: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        repo.filterByRisksExclusive(
            teenager, old, hepatic, renal, diabetes, pregnancy, lactation, child
        )

    override fun update(model: Product) = repo.update(model)
}