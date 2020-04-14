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

package cu.jesusd0897.farmakit.database.model.minimal

import androidx.room.ColumnInfo
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.CATEGORY
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.ID
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.IS_FAVORITE
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.NAME
import cu.jesusd0897.farmakit.database.NaturalProductsColumns.SCIENTIFIC_NAME
import cu.jesusd0897.farmakit.model.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MinNaturalProduct(
    @field:ColumnInfo(name = ID) val id: Int,
    @field:ColumnInfo(name = NAME) var name: String,
    @field:ColumnInfo(name = SCIENTIFIC_NAME) var scientificName: String,
    @field:ColumnInfo(name = CATEGORY) var category: String,
    @field:ColumnInfo(name = IS_FAVORITE, defaultValue = "0") var isFavorite: Boolean
) : Model {

    override fun match(query: String?): Boolean =
        if (query == null) true
        else (name.contains(query, true)
                || scientificName.contains(query, true)
                || category.contains(query, true))

}