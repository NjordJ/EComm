package com.iruda.ecomm.domain.category.usecases

import com.iruda.ecomm.domain.category.entities.Category
import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class GetCategoryListUseCase(
    private val repository: CategoryRepository
) {

    operator fun invoke(): List<Category> = repository.getCategoryList()
}