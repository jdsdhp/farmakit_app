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

    override fun update(model: NaturalProduct) {
        UpdateTask(dao).execute(model)
    }

    private class UpdateTask(DAO: NaturalProductDao) :
        HandlerTask<NaturalProductDao, NaturalProduct>(DAO) {
        override fun doInBackground(vararg model: NaturalProduct): Unit? =
            super.DAO.update(model[0])
    }

}