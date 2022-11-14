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
    private val factory: CartApiFactory,
    private val cartDao: CartDao,
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

    override suspend fun loadData(userId: Int) {
        val cart = factory.apiService.getUserCart(userId = userId.toString()).carts[FIRST_USER_CART]
        val cartProducts = cart.products
        cartDao.insertCart(cart = cart)
    }

    companion object {

        private const val FIRST_USER_CART = 0
    }
}