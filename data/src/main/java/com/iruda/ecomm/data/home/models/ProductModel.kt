package com.iruda.ecomm.data.home.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iruda.ecomm.domain.category.entities.Category

@Entity(tableName = "product_table")
data class ProductModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @Embedded(prefix = "rating_") val rating: RatingModel
)

@Entity(tableName = "rating_table", primaryKeys = ["rate", "count"])
data class RatingModel(
    val rate: Double,
    val count: Int
)