package com.iruda.ecomm.domain.cart.usecases

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.cart.entities.CartProduct
import com.iruda.ecomm.domain.cart.repositories.CartRepository

class GetCartListUseCase(
    private val repository: CartRepository
) {

    operator fun invoke(searchQuery: String): LiveData<List<CartProduct>> =
        repository.getCartList(searchQuery = searchQuery)
}