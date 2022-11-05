package com.iruda.ecomm.data.product.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iruda.ecomm.data.product.models.ProductModel

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getProductList(): LiveData<List<ProductModel>>

    @Query("SELECT * FROM product_table WHERE id == :id LIMIT 1")
    fun getProduct(id: Int): LiveData<ProductModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(products: List<ProductModel>)

    @Query("SELECT * FROM product_table WHERE category == :categoryName")
    fun getProductListInCategory(categoryName: String): LiveData<List<ProductModel>>
}