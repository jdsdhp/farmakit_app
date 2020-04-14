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
import cu.jesusd0897.farmakit.database.dao.ProductDao
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct

class ProductRepo(context: Context) : ProductDao {

    private val dao: ProductDao = KRoomDatabase.getInstance(context)!!.productDao()
    override val all: LiveData<MutableList<MinProduct>> = dao.all
    override fun findByCode(code: String): LiveData<Product> = dao.findByCode(code)
    override fun get(id: String): LiveData<Product> = dao.get(id)

    override fun filterByRisksInclusive(
        teenager: String, old: String, hepatic: String, renal: String, diabetes: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        dao.filterByRisksInclusive(
            teenager, old, hepatic, renal, diabetes, pregnancy, lactation, child
        )

    override fun filterByRisksExclusive(
        teenager: String, old: String, hepatic: String, renal: String, diabetes: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        dao.filterByRisksExclusive(
            teenager, old, hepatic, renal, diabetes, pregnancy, lactation, child
        )

    override fun update(model: Product) {
        UpdateTask(dao).execute(model)
    }

    private class UpdateTask(DAO: ProductDao) :
        HandlerTask<ProductDao, Product>(DAO) {
        override fun doInBackground(vararg model: Product): Unit? = super.DAO.update(model[0])
    }
}