package com.iruda.ecomm.domain.auth.usecases

import com.iruda.ecomm.domain.auth.repositories.AuthRepository

class LogOutUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke() = repository.logOut()
}