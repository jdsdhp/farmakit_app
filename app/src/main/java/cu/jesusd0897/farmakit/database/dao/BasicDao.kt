package cu.jesusd0897.farmakit.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BasicDao<M> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: M)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg models: M)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<M>)

    @Update
    fun update(model: M)

    @Update
    fun update(vararg models: M)

    @Update
    fun update(models: List<M>)

    @Delete
    fun delete(model: M)

    @Delete
    fun delete(vararg models: M)

    @Delete
    fun delete(models: List<M>)
}