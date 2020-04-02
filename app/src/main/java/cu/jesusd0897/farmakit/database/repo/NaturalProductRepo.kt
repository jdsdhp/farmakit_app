package cu.jesusd0897.farmakit.database.repo

import android.content.Context
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.KRoomDatabase
import cu.jesusd0897.farmakit.database.dao.NaturalProductDao
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct

class NaturalProductRepo(context: Context) : NaturalProductDao {

    private val dao: NaturalProductDao = KRoomDatabase.getInstance(context)!!.naturalProductDao()

    override val all: LiveData<MutableList<MinNaturalProduct>> = dao.all

    override fun get(id: String): LiveData<NaturalProduct> = dao.get(id)

    override fun insert(model: NaturalProduct) {
        InsertTask(dao).execute(model)
    }

    override fun insert(vararg models: NaturalProduct) {
        InsertManyTask(dao).execute(*models)
    }

    override fun insert(models: List<NaturalProduct>) {
        InsertManyTaskList(dao).execute(models)
    }

    override fun update(model: NaturalProduct) {
        UpdateTask(dao).execute(model)
    }

    override fun update(vararg models: NaturalProduct) {
        UpdateManyTask(dao).execute(*models)
    }

    override fun update(models: List<NaturalProduct>) {
        UpdateManyTaskList(dao).execute(models)
    }

    override fun delete(model: NaturalProduct) {
        DeleteTask(dao).execute(model)
    }

    override fun delete(vararg models: NaturalProduct) {
        DeleteManyTask(dao).execute(*models)
    }

    override fun delete(models: List<NaturalProduct>) {
        DeleteManyTaskList(dao).execute(models)
    }

    override fun deleteAll() {
        DeleteAllTask(dao).execute()
    }

    private class InsertTask internal constructor(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg params: NaturalProduct): Unit? =
            super.DAO.insert(params[0])
    }

    private class InsertManyTask internal constructor(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg naturalProducts: NaturalProduct): Unit? =
            super.DAO.insert(*naturalProducts)
    }

    private class InsertManyTaskList internal constructor(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, List<NaturalProduct>>(DAO) {
        override fun doInBackground(vararg naturalProducts: List<NaturalProduct>): Unit? =
            super.DAO.insert(naturalProducts[0])
    }

    private class UpdateTask(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg naturalProducts: NaturalProduct): Unit? =
            super.DAO.update(naturalProducts[0])
    }

    private class UpdateManyTask(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg naturalProducts: NaturalProduct): Unit? =
            super.DAO.update(*naturalProducts)
    }

    private class UpdateManyTaskList internal constructor(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, List<NaturalProduct>>(DAO) {
        override fun doInBackground(vararg naturalProducts: List<NaturalProduct>): Unit? =
            super.DAO.update(naturalProducts[0])
    }

    private class DeleteTask(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg naturalProducts: NaturalProduct): Unit? =
            super.DAO.delete(naturalProducts[0])
    }

    private class DeleteManyTask(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg naturalProducts: NaturalProduct): Unit? =
            super.DAO.delete(*naturalProducts)
    }

    private class DeleteManyTaskList internal constructor(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, List<NaturalProduct>>(DAO) {
        override fun doInBackground(vararg naturalProducts: List<NaturalProduct>): Unit? =
            super.DAO.delete(naturalProducts[0])
    }

    private class DeleteAllTask(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg naturalProducts: NaturalProduct): Unit? =
            super.DAO.deleteAll()
    }

}