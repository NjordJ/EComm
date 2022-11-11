package com.iruda.ecomm.data.auth.network

import com.iruda.ecomm.data.global.ApiFactory
import retrofit2.Retrofit

object AuthApiFactory : ApiFactory() {

    override val retrofit: Retrofit
        get() = super.retrofit

    val apiService: AuthApiService = retrofit.create(AuthApiService::class.java)
}