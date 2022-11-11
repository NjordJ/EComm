package com.iruda.ecomm.domain.auth.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.auth.entities.Auth

interface AuthRepository {

    fun authorizeWithEmail(email: String, password: String): LiveData<Auth>
}