package com.iruda.ecomm.domain.category.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.category.entities.Category
import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class GetCategoryListUseCase(
    private val repository: CategoryRepository
) {

    operator fun invoke(searchQuery: String): LiveData<List<Category>> =
        repository.getCategoryList(searchQuery = searchQuery)
}