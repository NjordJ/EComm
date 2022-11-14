package com.iruda.ecomm.domain.cart.usecases

import com.iruda.ecomm.domain.cart.repositories.CartRepository

class LoadCartDataUseCase(
    private val repository: CartRepository
) {

    suspend operator fun invoke(userId: Int) = repository.loadData(userId = userId)
}