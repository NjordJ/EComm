package com.iruda.ecomm.data.product.network

import com.iruda.ecomm.data.product.models.ProductsModel
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): ProductsModel
}