package com.iruda.ecomm.data.product.network

import com.iruda.ecomm.data.product.models.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): List<ProductModel>

    @GET("products/category/{categoryName}")
    suspend fun getProductListInCategory(@Path("categoryName") categoryName: String): List<ProductModel>
}