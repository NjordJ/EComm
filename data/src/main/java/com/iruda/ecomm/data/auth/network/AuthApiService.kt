package com.iruda.ecomm.data.auth.network

import com.iruda.ecomm.data.auth.models.AuthRequestModel
import com.iruda.ecomm.data.auth.models.AuthResponseModel
import com.iruda.ecomm.domain.auth.entities.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST
    fun loginWithEmail(@Body request: AuthRequestModel): Call<AuthResponseModel>
}