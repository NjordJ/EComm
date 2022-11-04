package com.iruda.ecomm.data.category.network

import com.iruda.ecomm.data.category.models.CategoryModel
import retrofit2.http.GET

interface CategoryApiService {

    @GET("products/categories")
    suspend fun getAllCategories(): List<String>
}