package com.iruda.ecomm.data.global

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.models.CategoryModel
import com.iruda.ecomm.data.product.database.ProductDao
import com.iruda.ecomm.data.product.models.ProductModel
import com.iruda.ecomm.data.product.models.RatingModel

@Database(
    entities = [ProductModel::class, RatingModel::class, CategoryModel::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DB_NAME = "ecomm.db"
    }

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}