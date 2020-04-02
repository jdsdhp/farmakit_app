package cu.jesusd0897.farmakit.database.repo

import android.os.AsyncTask

internal abstract class HandlerTask<D, M>(val DAO: D) : AsyncTask<M, Unit?, Unit?>()