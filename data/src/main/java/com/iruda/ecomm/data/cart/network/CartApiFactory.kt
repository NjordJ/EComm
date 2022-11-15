package com.iruda.ecomm.data.cart.network

import com.iruda.ecomm.data.global.ApiFactory
import retrofit2.Retrofit

object CartApiFactory : ApiFactory() {

    override val retrofit: Retrofit
        get() = super.retrofit

    val apiService: CartApiService = retrofit.create(CartApiService::class.java)
}