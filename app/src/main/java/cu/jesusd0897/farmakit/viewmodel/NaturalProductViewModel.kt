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
import cu.jesusd0897.farmakit.database.dao.NaturalProductDao
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct
import cu.jesusd0897.farmakit.database.repo.NaturalProductRepo

class NaturalProductViewModel(app: Application) : AndroidViewModel(app), NaturalProductDao {
    private val repo: NaturalProductRepo = NaturalProductRepo(app)
    override val all: LiveData<MutableList<MinNaturalProduct>> = repo.all
    override fun get(id: String): LiveData<NaturalProduct> = repo.get(id)
    override fun update(model: NaturalProduct) = repo.update(model)
}