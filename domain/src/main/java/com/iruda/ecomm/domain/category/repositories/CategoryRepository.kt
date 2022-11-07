package com.iruda.ecomm.domain.category.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.category.entities.Category

interface CategoryRepository {

    fun getCategoryList(searchQuery: String): LiveData<List<Category>>

    fun getCategory(name: String): LiveData<Category>

    fun loadData()
}