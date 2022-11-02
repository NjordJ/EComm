package com.iruda.ecomm.domain.home.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.home.entities.Product

interface ProductRepository {

    fun getProductList(): LiveData<List<Product>>

    fun getProduct(id: Int): LiveData<Product>
}