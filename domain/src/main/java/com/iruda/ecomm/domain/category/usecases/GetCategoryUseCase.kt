package com.iruda.ecomm.domain.category.usecases

import com.iruda.ecomm.domain.category.entities.Category
import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class GetCategoryUseCase(
    private val repository: CategoryRepository
) {

    operator fun invoke(name: String): Category = repository.getCategory(name = name)
}