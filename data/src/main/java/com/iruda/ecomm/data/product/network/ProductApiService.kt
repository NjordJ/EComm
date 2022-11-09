package com.iruda.ecomm.data.product.network

import com.iruda.ecomm.data.product.models.ProductModel
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): List<ProductModel>
}