package com.iruda.ecomm.domain.auth.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.auth.entities.AuthResponse

interface AuthRepository {

    suspend fun authorizeWithEmail(email: String, password: String)

    fun getAuthResponse(): LiveData<AuthResponse>
}