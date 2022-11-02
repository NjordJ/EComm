package com.iruda.ecomm.domain.cart.usecases

import com.iruda.ecomm.domain.cart.entities.Cart
import com.iruda.ecomm.domain.cart.repositories.CartRepository

class GetCartListUseCase(
    private val repository: CartRepository
) {

    operator fun invoke(): List<Cart> = repository.getCartList()
}