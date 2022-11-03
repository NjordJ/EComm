package com.iruda.ecomm.data.home.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iruda.ecomm.domain.category.entities.Category

@Entity(tableName = "product")
data class ProductModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: Category,
    val image: String,
    val rating: Rating
) {

    data class Rating(
        val rate: Double,
        val count: Int
    )
}