package com.iruda.ecomm.domain.product.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) : Parcelable {

    @Parcelize
    data class ProductImages(
        val id: Int,
        val url: String
    ) : Parcelable
}