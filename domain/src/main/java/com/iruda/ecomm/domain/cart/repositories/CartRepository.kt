package com.iruda.ecomm.domain.cart.repositories

import com.iruda.ecomm.domain.cart.entities.Cart

interface CartRepository {

    fun getCartList(): List<Cart>

    fun getCart(id: Int): Cart
}