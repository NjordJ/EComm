package com.iruda.ecomm.domain.home.usecases

import com.iruda.ecomm.domain.home.repositories.ProductRepository

class LoadProductDataUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke() = repository.loadData()
}