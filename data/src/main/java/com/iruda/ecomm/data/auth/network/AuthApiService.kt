package com.iruda.ecomm.data.auth.network

import com.iruda.ecomm.data.auth.models.AuthModel
import retrofit2.Call
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun authWithEmail(email: String, password: String): Call<AuthModel>
}