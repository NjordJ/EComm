package com.iruda.ecomm.domain.cart.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.cart.entities.Cart
import com.iruda.ecomm.domain.cart.entities.CartProduct

interface CartRepository {

    fun getCart(): LiveData<Cart>

    fun getCartList(searchQuery: String): LiveData<List<CartProduct>>

    suspend fun loadData(userId: Int)
}