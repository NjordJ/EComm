package com.iruda.ecomm.data.auth.models

import com.google.gson.annotations.SerializedName

data class AuthRequestModel(
    @SerializedName("username")
    val userName: String,
    val password: String
)