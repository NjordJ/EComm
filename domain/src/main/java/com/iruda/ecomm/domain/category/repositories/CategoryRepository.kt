package com.iruda.ecomm.domain.category.repositories

import com.iruda.ecomm.domain.category.entities.Category

interface CategoryRepository {

    fun getCategoryList(): List<Category>
}