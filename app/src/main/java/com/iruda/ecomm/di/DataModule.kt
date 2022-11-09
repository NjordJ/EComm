package com.iruda.ecomm.di

import androidx.room.Room
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.mappers.CategoryMapper
import com.iruda.ecomm.data.global.AppDatabase
import com.iruda.ecomm.data.product.database.ProductDao
import com.iruda.ecomm.data.product.mappers.ProductMapper
import org.koin.dsl.module

val dataModule = module {

    //mappers
    single { ProductMapper() }
    single { CategoryMapper() }

    //database
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    //dao
    single<ProductDao> {
        val database = get<AppDatabase>()
        database.productDao()
    }

    single<CategoryDao> {
        val database = get<AppDatabase>()
        database.categoryDao()
    }

}