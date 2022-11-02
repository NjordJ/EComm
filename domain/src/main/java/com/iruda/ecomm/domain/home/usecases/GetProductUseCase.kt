package com.iruda.ecomm.domain.home.usecases

import com.iruda.ecomm.domain.home.entities.Product
import com.iruda.ecomm.domain.home.repositories.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(id: Int): Product = repository.getProduct(id = id)
}