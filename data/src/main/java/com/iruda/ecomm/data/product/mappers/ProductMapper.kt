package com.iruda.ecomm.data.product.mappers

import com.iruda.ecomm.data.product.models.ProductModel
import com.iruda.ecomm.data.product.models.RatingModel
import com.iruda.ecomm.domain.product.entities.Product

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

    private fun mapModelRatingToEntityRating(model: RatingModel) = Product.Rating(
        rate = model.rate,
        count = model.count
    )
}