package com.iruda.ecomm.presentation.account.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iruda.ecomm.domain.auth.usecases.AuthorizeWithEmailUseCase
import com.iruda.ecomm.domain.auth.usecases.GetAuthResponseUseCase

class AccountViewModel(
    application: Application,
    private val getAuthResponseUseCase: GetAuthResponseUseCase,
    private val authorizeWithEmailUseCase: AuthorizeWithEmailUseCase
) : AndroidViewModel(application) {

    init {

    }

    suspend fun authorize() {
        authorizeWithEmailUseCase()
    }

    fun getUser() =
        getAuthResponseUseCase()



}