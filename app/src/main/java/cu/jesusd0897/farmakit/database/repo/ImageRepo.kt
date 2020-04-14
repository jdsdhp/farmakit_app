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

package cu.jesusd0897.farmakit.database.repo

import android.content.Context
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.KRoomDatabase
import cu.jesusd0897.farmakit.database.dao.ImageDao
import cu.jesusd0897.farmakit.database.model.Image

class ImageRepo(context: Context) : ImageDao {
    private val dao: ImageDao = KRoomDatabase.getInstance(context)!!.imageDao()
    override fun get(typeId: String, type: String): LiveData<Image> = dao.get(typeId, type)
}