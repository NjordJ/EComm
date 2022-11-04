package com.iruda.ecomm.data.global

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.models.CategoryModel
import com.iruda.ecomm.data.home.database.ProductDao
import com.iruda.ecomm.data.home.models.ProductModel
import com.iruda.ecomm.data.home.models.RatingModel

@Database(
    entities = [ProductModel::class, RatingModel::class, CategoryModel::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "ecomm.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}