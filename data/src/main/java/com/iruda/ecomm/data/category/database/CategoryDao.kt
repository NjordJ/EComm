package com.iruda.ecomm.data.category.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iruda.ecomm.data.category.models.CategoryModel

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table")
    fun getCategoryList(): LiveData<List<CategoryModel>>

    @Query("SELECT * FROM category_table WHERE name == :name LIMIT 1")
    fun getCategory(name: String): LiveData<CategoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryList(categories: List<CategoryModel>)
}