package com.iruda.ecomm.domain.home.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.home.entities.Product
import com.iruda.ecomm.domain.home.repositories.ProductRepository

class GetProductListUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(): LiveData<List<Product>> = repository.getProductList()
}