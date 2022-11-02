package com.iruda.ecomm.domain.home.entities

import com.iruda.ecomm.domain.category.entities.Category

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: Category,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)