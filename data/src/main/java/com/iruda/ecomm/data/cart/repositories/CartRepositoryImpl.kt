package com.iruda.ecomm.data.cart.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.iruda.ecomm.data.cart.database.CartDao
import com.iruda.ecomm.data.cart.mappers.CartMapper
import com.iruda.ecomm.data.cart.network.CartApiFactory
import com.iruda.ecomm.domain.cart.entities.Cart
import com.iruda.ecomm.domain.cart.entities.CartProduct
import com.iruda.ecomm.domain.cart.repositories.CartRepository

class CartRepositoryImpl(
    private val cartDao: CartDao,
    private val factory: CartApiFactory,
    private val mapper: CartMapper
) : CartRepository {

    override fun getCart(): LiveData<Cart> {
        return Transformations.map(cartDao.getCart()) {
            mapper.mapCartModelToEntity(it)
        }
    }

    override fun getCartList(searchQuery: String): LiveData<List<CartProduct>> {
        return Transformations.map(
            cartDao.getCartList(searchQuery = searchQuery)
        ) {
            it.map { model ->
                mapper.mapCartProductModelToEntity(model = model)
            }
        }
    }

    override suspend fun loadData() {
        val cart = factory.apiService.getUserCart(userId = "5").carts[0]
        cartDao.insertCart(cart = cart)
    }
}