package com.iruda.ecomm.data.auth.network

import com.iruda.ecomm.data.auth.models.AuthRequestModel
import com.iruda.ecomm.data.auth.models.AuthResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {

    @Headers(
        "Content-type: application/json"
    )
    @POST("auth/login")
    fun loginWithEmail(@Body request: AuthRequestModel): Call<AuthResponseModel>
}