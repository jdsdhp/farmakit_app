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

package cu.jesusd0897.farmakit.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cu.jesusd0897.farmakit.database.ImagesColumns.ID
import cu.jesusd0897.farmakit.database.ImagesColumns.NAME
import cu.jesusd0897.farmakit.database.ImagesColumns.TYPE
import cu.jesusd0897.farmakit.database.ImagesColumns.TYPE_ID
import cu.jesusd0897.farmakit.database.Tables
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Tables.IMAGES)
@Parcelize
data class Image(
    @PrimaryKey @field:ColumnInfo(name = ID) val id: Int,
    @field:ColumnInfo(name = TYPE_ID) var typeId: Int,
    @field:ColumnInfo(name = TYPE) var type: String,
    @field:ColumnInfo(name = NAME) var name: String
) : Parcelable