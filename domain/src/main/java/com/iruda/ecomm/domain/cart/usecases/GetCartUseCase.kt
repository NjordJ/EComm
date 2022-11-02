package com.iruda.ecomm.domain.cart.usecases

import com.iruda.ecomm.domain.cart.entities.Cart
import com.iruda.ecomm.domain.cart.repositories.CartRepository

class GetCartUseCase(
    private val repository: CartRepository
) {

    operator fun invoke(id: Int): Cart = repository.getCart(id = id)
}