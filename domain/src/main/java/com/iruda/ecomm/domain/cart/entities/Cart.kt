package com.iruda.ecomm.domain.cart.entities

data class Cart(
    val id: Int,
    val products: CartProduct,
    val total: Int,
    val discountedTotal: Int,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
) {

    data class CartProduct(
        val productId: Int,
        val title: String,
        val price: Double,
        val quantity: Int,
        val total: Int,
        val discountPercentage: Double,
        val discountedPrice: Double
    )
}

