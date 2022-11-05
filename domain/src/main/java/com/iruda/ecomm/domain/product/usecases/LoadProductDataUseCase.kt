package com.iruda.ecomm.domain.product.usecases

import com.iruda.ecomm.domain.product.repositories.ProductRepository

class LoadProductDataUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke() = repository.loadData()
}