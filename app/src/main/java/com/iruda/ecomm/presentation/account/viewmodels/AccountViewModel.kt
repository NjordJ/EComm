package com.iruda.ecomm.presentation.account.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iruda.ecomm.domain.auth.usecases.GetAuthResponseUseCase
import com.iruda.ecomm.domain.auth.usecases.LogOutUseCase

class AccountViewModel(
    application: Application,
    private val logOutUseCase: LogOutUseCase,
    private val getAuthResponseUseCase: GetAuthResponseUseCase
) : AndroidViewModel(application) {

    fun logOutFromAccount() = logOutUseCase()

    fun getUser() = getAuthResponseUseCase()
}