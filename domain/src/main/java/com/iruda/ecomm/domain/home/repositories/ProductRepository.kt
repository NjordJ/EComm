package com.iruda.ecomm.domain.home.repositories

import com.iruda.ecomm.domain.home.entities.Product

interface ProductRepository {

    fun getProductList(): List<Product>

    fun getProduct(id: Int): Product
}