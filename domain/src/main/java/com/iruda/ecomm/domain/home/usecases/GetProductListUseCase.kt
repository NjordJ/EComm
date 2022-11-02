package com.iruda.ecomm.domain.home.usecases

import com.iruda.ecomm.domain.home.entities.Product
import com.iruda.ecomm.domain.home.repositories.ProductRepository

class GetProductListUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(): List<Product> = repository.getProductList()
}