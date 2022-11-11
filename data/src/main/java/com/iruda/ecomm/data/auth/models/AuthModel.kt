package com.iruda.ecomm.data.auth.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_table")
data class AuthModel(
    @PrimaryKey
    val token: String
)