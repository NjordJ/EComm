package com.iruda.ecomm.domain.auth.repositories

import com.iruda.ecomm.domain.auth.entities.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun authorizeWithEmail(email: String, password: String)

    fun getAuthResponse(): Flow<AuthResponse>

    fun logOut()
}