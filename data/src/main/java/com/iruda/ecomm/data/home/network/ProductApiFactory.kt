package com.iruda.ecomm.data.home.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductApiFactory {

    private const val BASE_URL = "https://fakestoreapi.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService: ProductApiService = retrofit.create(ProductApiService::class.java)
}