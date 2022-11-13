package com.iruda.ecomm.domain.auth.usecases

import com.iruda.ecomm.domain.auth.repositories.AuthRepository

class AuthorizeWithEmailUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() = repository.authorizeWithEmail(

    )
}