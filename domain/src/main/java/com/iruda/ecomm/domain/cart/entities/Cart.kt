package com.iruda.ecomm.domain.cart.entities

import com.iruda.ecomm.domain.account.entities.User
import com.iruda.ecomm.domain.home.entities.Product

data class Cart(
    val id: Int,
    val userId: User,
    val date: String,
    val products: CartProduct
) {

    data class CartProduct(
        val productId: Product,
        val quantity: Int
    )
}

