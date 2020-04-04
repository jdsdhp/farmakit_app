package cu.jesusd0897.farmakit.database.dao

import androidx.room.Update

interface BasicDao<M> {
    @Update
    fun update(model: M)
}