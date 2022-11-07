package com.iruda.ecomm.domain.product.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.repositories.ProductRepository

class GetProductListInCategoryUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke(
        categoryName: String,
        searchQuery: String
    ): LiveData<List<Product>> =
        repository.getProductListInCategory(categoryName = categoryName, searchQuery = searchQuery)
}