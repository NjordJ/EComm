package com.iruda.ecomm.data.home.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iruda.ecomm.data.home.models.ProductModel

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getProductList(): LiveData<List<ProductModel>>

    @Query("SELECT * FROM product WHERE id == :id LIMIT 1")
    fun getProduct(id: Int): LiveData<ProductModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(products: List<ProductModel>)
}