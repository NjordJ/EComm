package com.iruda.ecomm.domain.account.entities

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val userName: String,
    val password: String,
    val image: String
)

