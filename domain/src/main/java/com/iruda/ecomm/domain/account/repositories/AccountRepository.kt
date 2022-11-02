package com.iruda.ecomm.domain.account.repositories

import com.iruda.ecomm.domain.account.entities.User

interface AccountRepository {

    fun getUser(id: Int): User
}