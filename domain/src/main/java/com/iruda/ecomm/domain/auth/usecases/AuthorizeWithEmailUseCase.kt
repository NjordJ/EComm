package com.iruda.ecomm.domain.auth.usecases

import com.iruda.ecomm.domain.auth.repositories.AuthRepository

class AuthorizeWithEmailUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(email: String, password: String) = repository.authorizeWithEmail(
        email = email,
        password = password
    )
}