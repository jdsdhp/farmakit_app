/*
 * Copyright (c) 2020 jesusd0897.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cu.jesusd0897.farmakit.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import cu.jesusd0897.farmakit.database.dao.ImageDao
import cu.jesusd0897.farmakit.database.dao.NaturalProductDao
import cu.jesusd0897.farmakit.database.dao.ProductDao
import cu.jesusd0897.farmakit.database.model.Image
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.Product
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
    abstract fun imageDao(): ImageDao

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