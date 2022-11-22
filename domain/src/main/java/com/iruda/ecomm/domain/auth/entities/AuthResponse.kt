package com.iruda.ecomm.domain.auth.entities

data class AuthResponse(
    val id: Int,
    val token: String,
    val isAuthorized: Boolean
)