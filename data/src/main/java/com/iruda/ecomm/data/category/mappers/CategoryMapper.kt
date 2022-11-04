package com.iruda.ecomm.data.category.mappers

import com.iruda.ecomm.data.category.models.CategoryModel
import com.iruda.ecomm.domain.category.entities.Category

class CategoryMapper {

    fun mapModelToEntity(model: CategoryModel) = Category(
        name = model.name
    )

    fun mapStringToModel(category: String) = CategoryModel(
        name = category
    )
}