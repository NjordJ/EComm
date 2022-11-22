package com.iruda.ecomm.presentation.account.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iruda.ecomm.domain.auth.usecases.AuthorizeWithEmailUseCase
import com.iruda.ecomm.domain.auth.usecases.GetAuthResponseUseCase

class LoginViewModel(
    application: Application,
    private val getAuthResponseUseCase: GetAuthResponseUseCase,
    private val authorizeWithEmailUseCase: AuthorizeWithEmailUseCase
) : AndroidViewModel(application) {

    init {

    }

    suspend fun authorize(email: String, password: String) {
        authorizeWithEmailUseCase(
            email = email,
            password = password
        )
    }

    fun getUser() =
        getAuthResponseUseCase()

}