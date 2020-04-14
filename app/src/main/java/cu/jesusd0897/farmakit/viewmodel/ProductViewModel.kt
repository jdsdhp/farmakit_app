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

package cu.jesusd0897.farmakit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cu.jesusd0897.farmakit.database.dao.ProductDao
import cu.jesusd0897.farmakit.database.model.Product
import cu.jesusd0897.farmakit.database.model.minimal.MinProduct
import cu.jesusd0897.farmakit.database.repo.ProductRepo

class ProductViewModel(app: Application) : AndroidViewModel(app), ProductDao {
    private val repo: ProductRepo = ProductRepo(app)
    override val all: LiveData<MutableList<MinProduct>> = repo.all
    override fun findByCode(code: String): LiveData<Product> = repo.findByCode(code)
    override fun get(id: String): LiveData<Product> = repo.get(id)

    override fun filterByRisksInclusive(
        teenager: String, old: String, hepatic: String, renal: String, diabetes: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        repo.filterByRisksInclusive(
            teenager, old, hepatic, renal, diabetes, pregnancy, lactation, child
        )

    override fun filterByRisksExclusive(
        teenager: String, old: String, hepatic: String, renal: String, diabetes: String,
        pregnancy: String, lactation: String, child: String
    ): LiveData<MutableList<MinProduct>> =
        repo.filterByRisksExclusive(
            teenager, old, hepatic, renal, diabetes, pregnancy, lactation, child
        )

    override fun update(model: Product) = repo.update(model)
}