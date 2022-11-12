package com.iruda.ecomm.data.product.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductModel(
    @PrimaryKey(autoGenerate = false)
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
    //@Embedded(prefix = "product_image_")
    val images: List<String>
)

@Entity(tableName = "product_images_table")
data class ProductImagesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val url: String
)