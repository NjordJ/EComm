package com.iruda.ecomm.data.auth.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_response_table")
data class AuthResponseModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val token: String
)