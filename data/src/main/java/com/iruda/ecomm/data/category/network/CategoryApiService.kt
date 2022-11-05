package com.iruda.ecomm.data.category.network

import retrofit2.http.GET

interface CategoryApiService {

    @GET("products/categories")
    suspend fun getAllCategories(): List<String>
}