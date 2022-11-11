package com.iruda.ecomm.data.category.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryModel (

    @PrimaryKey
    val name: String
)