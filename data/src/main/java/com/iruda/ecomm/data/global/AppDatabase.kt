package com.iruda.ecomm.data.global

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iruda.ecomm.data.auth.database.AuthDao
import com.iruda.ecomm.data.auth.models.AuthResponseModel
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.models.CategoryModel
import com.iruda.ecomm.data.product.database.ProductDao
import com.iruda.ecomm.data.product.database.converters.ImagesConverter
import com.iruda.ecomm.data.product.models.ProductImagesModel
import com.iruda.ecomm.data.product.models.ProductModel

@Database(
    entities = [ProductModel::class, ProductImagesModel::class, CategoryModel::class,
        AuthResponseModel::class],
    version = 9,
    exportSchema = false
)
@TypeConverters(ImagesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DB_NAME = "ecomm.db"
    }

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun authDao(): AuthDao
}