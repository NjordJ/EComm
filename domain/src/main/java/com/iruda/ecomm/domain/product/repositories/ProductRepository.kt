package com.iruda.ecomm.domain.product.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.product.entities.Product

interface ProductRepository {

    fun getProductList(): LiveData<List<Product>>

    fun getProduct(id: Int): LiveData<Product>

    fun loadData()

    suspend fun getProductListInCategory(categoryName: String): LiveData<List<Product>>
}