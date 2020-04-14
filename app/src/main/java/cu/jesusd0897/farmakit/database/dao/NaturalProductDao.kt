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

package cu.jesusd0897.farmakit.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.CATEGORY
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.ID
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.IS_FAVORITE
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.NAME
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.SCIENTIFIC_NAME
import cu.jesusd0897.farmakit.database.Tables.NATURAL_PRODUCTS
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.model.minimal.MinNaturalProduct

@Dao
interface NaturalProductDao : BasicDao<NaturalProduct> {

    @Query("SELECT * FROM $NATURAL_PRODUCTS WHERE $ID LIKE :id")
    fun get(id: String): LiveData<NaturalProduct>

    @get:Query(
        "SELECT $ID, $NAME, $SCIENTIFIC_NAME, $CATEGORY, $IS_FAVORITE " +
                "FROM $NATURAL_PRODUCTS ORDER BY $IS_FAVORITE DESC, $NAME"
    )
    val all: LiveData<MutableList<MinNaturalProduct>>

}