package cu.jesusd0897.farmakit.database.repo

import android.content.Context
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.KRoomDatabase
import cu.jesusd0897.farmakit.database.dao.ProductDao
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct

class ProductRepo(context: Context) : ProductDao {

    private val dao: ProductDao = KRoomDatabase.getInstance(context)!!.productDao()

    override val all: LiveData<MutableList<MinProduct>> = dao.all

    override fun filterByPopulation(
        old: String, hepatic: String, renal: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        dao.filterByPopulation(old, hepatic, renal, pregnancy, lactation, child)

    override fun filterByPopulationExclusive(
        old: String, hepatic: String, renal: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        dao.filterByPopulationExclusive(old, hepatic, renal, pregnancy, lactation, child)

    override fun findByCode(code: String): LiveData<Product> = dao.findByCode(code)

    override fun get(id: String): LiveData<Product> = dao.get(id)

    override fun insert(model: Product) {
        InsertTask(dao).execute(model)
    }

    override fun insert(vararg models: Product) {
        InsertManyTask(dao).execute(*models)
    }

    override fun insert(models: List<Product>) {
        InsertManyTaskList(dao).execute(models)
    }

    override fun update(model: Product) {
        UpdateTask(dao).execute(model)
    }

    override fun update(vararg models: Product) {
        UpdateManyTask(dao).execute(*models)
    }

    override fun update(models: List<Product>) {
        UpdateManyTaskList(dao).execute(models)
    }

    override fun delete(model: Product) {
        DeleteTask(dao).execute(model)
    }

    override fun delete(vararg models: Product) {
        DeleteManyTask(dao).execute(*models)
    }

    override fun delete(models: List<Product>) {
        DeleteManyTaskList(dao).execute(models)
    }

    override fun deleteAll() {
        DeleteAllTask(dao).execute()
    }

    private class InsertTask internal constructor(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg params: Product): Unit? =
            super.DAO.insert(params[0])
    }

    private class InsertManyTask internal constructor(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg naturalProducts: Product): Unit? =
            super.DAO.insert(*naturalProducts)
    }

    private class InsertManyTaskList internal constructor(DAO: ProductDao) :
        HandlerTask<ProductDao, List<Product>>(DAO) {
        override fun doInBackground(vararg naturalProducts: List<Product>): Unit? =
            super.DAO.insert(naturalProducts[0])
    }

    private class UpdateTask(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg naturalProducts: Product): Unit? =
            super.DAO.update(naturalProducts[0])
    }

    private class UpdateManyTask(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg naturalProducts: Product): Unit? =
            super.DAO.update(*naturalProducts)
    }

    private class UpdateManyTaskList internal constructor(DAO: ProductDao) :
        HandlerTask<ProductDao, List<Product>>(DAO) {
        override fun doInBackground(vararg naturalProducts: List<Product>): Unit? =
            super.DAO.update(naturalProducts[0])
    }

    private class DeleteTask(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg naturalProducts: Product): Unit? =
            super.DAO.delete(naturalProducts[0])
    }

    private class DeleteManyTask(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg naturalProducts: Product): Unit? =
            super.DAO.delete(*naturalProducts)
    }

    private class DeleteManyTaskList internal constructor(DAO: ProductDao) :
        HandlerTask<ProductDao, List<Product>>(DAO) {
        override fun doInBackground(vararg naturalProducts: List<Product>): Unit? =
            super.DAO.delete(naturalProducts[0])
    }

    private class DeleteAllTask(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg naturalProducts: Product): Unit? =
            super.DAO.deleteAll()
    }

}