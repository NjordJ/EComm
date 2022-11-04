package com.iruda.ecomm.domain.category.usecases

import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class LoadCategoryDataUseCase(
    private val repository: CategoryRepository
) {

    operator fun invoke() = repository.loadData()
}