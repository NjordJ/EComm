package com.iruda.ecomm.data.product.network

import com.iruda.ecomm.data.global.ApiFactory
import retrofit2.Retrofit

object ProductApiFactory : ApiFactory() {

    override val retrofit: Retrofit
        get() = super.retrofit

    val apiService: ProductApiService = retrofit.create(ProductApiService::class.java)
}