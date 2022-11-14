package com.iruda.ecomm.domain.cart.repositories

import androidx.lifecycle.LiveData
import com.iruda.ecomm.domain.cart.entities.Cart

interface CartRepository {

    fun getCartList(userId: Int): LiveData<List<Cart>>

    fun getCart(id: Int): LiveData<Cart>
}