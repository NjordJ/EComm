package com.iruda.ecomm.domain.account.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.account.entities.User

interface AccountRepository {

    fun getUser(id: Int): LiveData<User>
}