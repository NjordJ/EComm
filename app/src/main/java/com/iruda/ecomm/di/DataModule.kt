package com.iruda.ecomm.di

import androidx.room.Room
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.mappers.CategoryMapper
import com.iruda.ecomm.data.category.network.CategoryApiFactory
import com.iruda.ecomm.data.category.repositories.CategoryRepositoryImpl
import com.iruda.ecomm.data.category.workers.RefreshCategoriesWorker
import com.iruda.ecomm.data.global.AppDatabase
import com.iruda.ecomm.data.product.database.ProductDao
import com.iruda.ecomm.data.product.mappers.ProductMapper
import com.iruda.ecomm.data.product.network.ProductApiFactory
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.data.product.workers.RefreshProductsWorker
import com.iruda.ecomm.domain.category.repositories.CategoryRepository
import com.iruda.ecomm.domain.product.repositories.ProductRepository
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val dataModule = module {

    // Mappers
    single { ProductMapper() }
    single { CategoryMapper() }

    // Database
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Dao
    single<ProductDao> {
        val database = get<AppDatabase>()
        database.productDao()
    }

    single<CategoryDao> {
        val database = get<AppDatabase>()
        database.categoryDao()
    }

    // Repository
    single<ProductRepository> {
        ProductRepositoryImpl(
            application = get(),
            productDao = get(),
            mapper = get()
        )
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            application = get(),
            categoryDao = get(),
            mapper = get()
        )
    }

    // Network
    single<ProductApiFactory> {
        ProductApiFactory
    }

    single<CategoryApiFactory> {
        CategoryApiFactory
    }

    // Workmanager
    worker<RefreshProductsWorker> {
        RefreshProductsWorker(
            context = get(),
            workerParameters = get(),
            productDao = get(),
            factory = get()
        )
    }

    worker<RefreshCategoriesWorker> {
        RefreshCategoriesWorker(
            context = get(),
            workerParameters = get(),
            categoryDao = get(),
            mapper = get(),
            factory = get()
        )
    }

}