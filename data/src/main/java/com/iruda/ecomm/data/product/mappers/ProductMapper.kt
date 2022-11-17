package com.iruda.ecomm.data.product.mappers

import com.iruda.ecomm.data.product.models.ProductModel
import com.iruda.ecomm.domain.product.entities.Product

class ProductMapper {

    fun mapModelToEntity(model: ProductModel) = Product(
        id = model.id,
        title = model.title,
        description = model.description,
        price = model.price,
        discountPercentage = model.discountPercentage,
        rating = model.rating,
        stock = model.stock,
        brand = model.brand,
        category = model.category,
        thumbnail = model.thumbnail,
        images = model.images
    )
}