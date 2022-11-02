package com.iruda.ecomm.data.home.network

import com.iruda.ecomm.data.home.models.ProductModel
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts(): List<ProductModel>
}