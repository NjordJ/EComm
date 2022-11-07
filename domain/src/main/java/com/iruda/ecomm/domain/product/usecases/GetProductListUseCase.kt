package com.iruda.ecomm.domain.product.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.repositories.ProductRepository

class GetProductListUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(searchQuery: String): LiveData<List<Product>> =
        repository.getProductList(searchQuery = searchQuery)
}