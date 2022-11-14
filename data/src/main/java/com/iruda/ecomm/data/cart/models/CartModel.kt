package com.iruda.ecomm.data.cart.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartModel(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Embedded(prefix = "cart_product_") val products: List<CartProductModel>,
    val total: Int,
    val discountedTotal: Int,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)

@Entity(tableName = "cart_product_table")
data class CartProductModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Int,
    val discountPercentage: Double,
    val discountedPrice: Double
)