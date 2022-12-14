package com.iruda.ecomm.domain.product.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.product.entities.Product

interface ProductRepository {

    fun getProductList(searchQuery: String): LiveData<List<Product>>

    fun getProduct(id: Int): LiveData<Product>

    fun loadData()

    fun getProductListInCategory(categoryName: String, searchQuery: String): LiveData<List<Product>>
}