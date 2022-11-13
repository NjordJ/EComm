package com.iruda.ecomm.domain.auth.usecases

import com.iruda.ecomm.domain.auth.repositories.AuthRepository

class GetAuthResponseUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(id: Int) = repository.getAuthResponse(id = id)
}