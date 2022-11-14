package com.iruda.ecomm.data.cart.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iruda.ecomm.data.cart.models.CartModel
import com.iruda.ecomm.data.cart.models.CartProductModel

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_table")
    fun getCart(): LiveData<CartModel>

    @Query("SELECT * FROM cart_product_table WHERE title LIKE '%' || :searchQuery || '%'")
    fun getCartList(searchQuery: String): LiveData<List<CartProductModel>>

    @Query("SELECT * FROM cart_table WHERE id == :id LIMIT 1")
    fun getCartItem(id: Int): LiveData<CartModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartModel)
}