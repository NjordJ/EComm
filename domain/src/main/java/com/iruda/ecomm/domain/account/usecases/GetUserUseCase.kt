package com.iruda.ecomm.domain.account.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.account.entities.User
import com.iruda.ecomm.domain.account.repositories.AccountRepository

class GetUserUseCase(
    private val repository: AccountRepository
) {

    operator fun invoke(id: Int): LiveData<User> = repository.getUser(id = id)
}