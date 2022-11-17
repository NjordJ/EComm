package com.iruda.ecomm.data.cart.network

import com.iruda.ecomm.data.cart.models.CartsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApiService {

    @GET("carts/user/{userId}")
    suspend fun getUserCart(@Path("userId") userId: String): CartsModel
}