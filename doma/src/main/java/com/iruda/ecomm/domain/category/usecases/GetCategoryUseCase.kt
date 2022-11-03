package com.iruda.ecomm.domain.category.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.category.entities.Category
import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class GetCategoryUseCase(
    private val repository: CategoryRepository
) {

    operator fun invoke(name: String): LiveData<Category> = repository.getCategory(name = name)
}