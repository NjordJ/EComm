package com.iruda.ecomm.domain.auth.entities

data class AuthResponse(
    val id: Int,
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val token: String
)