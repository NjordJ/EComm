package com.iruda.ecomm.data.category.network

import com.iruda.ecomm.data.global.ApiFactory
import retrofit2.Retrofit

object CategoryApiFactory : ApiFactory() {

    override val retrofit: Retrofit
        get() = super.retrofit

    val apiService: CategoryApiService = retrofit.create(CategoryApiService::class.java)
}