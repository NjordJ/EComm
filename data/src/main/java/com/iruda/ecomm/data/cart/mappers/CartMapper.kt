package com.iruda.ecomm.data.cart.mappers

import com.iruda.ecomm.data.cart.models.CartModel
import com.iruda.ecomm.data.cart.models.CartProductModel
import com.iruda.ecomm.domain.cart.entities.Cart
import com.iruda.ecomm.domain.cart.entities.CartProduct

class CartMapper {

    fun mapCartModelToEntity(model: CartModel) = Cart(
        id = model.id,
        products = mapCartProductListModelToEntity(modelList = model.products),
        total = model.total,
        discountedTotal = model.discountedTotal,
        userId = model.userId,
        totalProducts = model.totalProducts,
        totalQuantity = model.totalQuantity
    )

    private fun mapCartProductListModelToEntity(modelList: List<CartProductModel>): List<CartProduct> {
        val list = mutableListOf<CartProduct>()

        for (model in modelList) {
            CartProduct(
                id = model.id,
                title = model.title,
                price = model.price,
                quantity = model.quantity,
                total = model.total,
                discountPercentage = model.discountPercentage,
                discountedPrice = model.discountedPrice
            )
        }

        return list
    }

    fun mapCartProductModelToEntity(model: CartProductModel) = CartProduct(
        id = model.id,
        title = model.title,
        price = model.price,
        quantity = model.quantity,
        total = model.total,
        discountPercentage = model.discountPercentage,
        discountedPrice = model.discountedPrice
    )
}