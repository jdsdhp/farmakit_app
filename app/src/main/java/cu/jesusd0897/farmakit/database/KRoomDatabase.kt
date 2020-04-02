package cu.jesusd0897.farmakit.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import cu.jesusd0897.farmakit.database.dao.NaturalProductDao
import cu.jesusd0897.farmakit.database.dao.ProductDao
import cu.jesusd0897.farmakit.database.model.Image
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.Product
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

@Database(
    entities = [NaturalProduct::class, Product::class, Image::class],
    version = DB_VERSION, exportSchema = false
)
abstract class KRoomDatabase : RoomDatabase() {

    abstract fun naturalProductDao(): NaturalProductDao
    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var database: KRoomDatabase? = null
        private lateinit var context: Context

        fun getInstance(context: Context): KRoomDatabase? {
            this.context = context
            if (database != null) return database
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, KRoomDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                database = instance
                return instance
            }
        }
    }

    override fun init(configuration: DatabaseConfiguration) {
        importExistingDatabase(configuration.context)
        super.init(configuration)
    }

    private fun importExistingDatabase(context: Context) {
        val bufferSize = 32768 //32
        val dbPath: File = context.getDatabasePath(DB_NAME)
        if (dbPath.exists()) return  // Database already exists
        val buffer = ByteArray(bufferSize)
        var totalBytesRead: Long = 0
        var totalBytesWritten: Long = 0
        var bytesRead = 0
        val assetDB: InputStream = context.assets.open(DB_NAME)
        dbPath.createNewFile()
        val realDB: OutputStream = FileOutputStream(dbPath)
        while (assetDB.read(buffer).also { bytesRead = it } > 0) {
            totalBytesRead += bytesRead
            realDB.write(buffer, 0, bytesRead)
            totalBytesWritten += bytesRead
        }
        realDB.flush()
        assetDB.close()
        realDB.close()
    }

}