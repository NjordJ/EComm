package com.iruda.ecomm.domain.product.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.repositories.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(id: Int): LiveData<Product> = repository.getProduct(id = id)
}