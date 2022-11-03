package com.iruda.ecomm.data.home.mappers

import com.iruda.ecomm.data.home.models.ProductModel
import com.iruda.ecomm.domain.home.entities.Product

class ProductMapper {

    fun mapModelToEntity(model: ProductModel) = Product(
        id = model.id,
        title = model.title,
        price = model.price,
        description = model.description,
        category = model.category,
        image = model.image,
        rating = mapModelRatingToEntityRating(model.rating)
    )

    private fun mapModelRatingToEntityRating(model: ProductModel.Rating) = Product.Rating(
        rate = model.rate,
        count = model.count
    )

    fun mapEntityToModel(entity: Product) = ProductModel(
        id = entity.id,
        title = entity.title,
        price = entity.price,
        description = entity.description,
        category = entity.category,
        image = entity.image,
        rating = mapEntityRatingToModelRating(entity.rating)
    )

    private fun mapEntityRatingToModelRating(entity: Product.Rating) = ProductModel.Rating(
        rate = entity.rate,
        count = entity.count
    )
}