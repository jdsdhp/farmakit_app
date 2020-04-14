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

const val DB_NAME = "data.db"
const val DB_VERSION = 1

object Tables {
    const val PRODUCTS = "products"
    const val NATURAL_PRODUCTS = "natural_products"
    const val IMAGES = "images"
}

object ImagesColumns {
    const val ID = "id"
    const val TYPE_ID = "type_id"
    const val TYPE = "type"
    const val NAME = "name"
}

object NaturalProductsColumns {
    const val ID = "id"
    const val NAME = "name"
    const val SCIENTIFIC_NAME = "scientific_name"
    const val CATEGORY = "category"
    const val DESCRIPTION = "description"
    const val IS_FAVORITE = "favorite"
}

object ProductsColumns {
    const val ID = "id"
    const val NAME = "name"
    const val PRESENTATION = "presentation"
    const val PRESENTATION_AMOUNT = "presentation_amount"
    const val CODE = "code"
    const val INTERNAL_NAME = "internal_name"
    const val REGULATION = "regulation"
    const val DISTRIBUTION = "distribution"
    const val CLASSIFICATION = "classification"
    const val CATEGORY = "category"
    const val LABORATORY = "laboratory"
    const val PRICE = "price"
    const val VIGILANCE = "vigilance"
    const val OMS = "oms"
    const val INDICATIONS = "indications"
    const val POSOLOGY = "posology"
    const val DESCRIPTION = "description"
    const val COMPOSITION = "composition"
    const val PHARMACODINAMIC = "pharmacodinamic"
    const val CONTRAINDICATIONS = "contraindications"
    const val REACTIONS = "reactions"
    const val PRECAUTIONS = "precautions"
    const val INTERACTIONS = "interactions"
    const val OVERDOSE_TREATMENT = "overdose_treatment"
    const val INFO = "info"
    const val POPULATION = "population"
    const val DOSAGE = "dosage"
    const val UPDATED_AT = "updated_at"
    const val IS_FAVORITE = "favorite"
}

