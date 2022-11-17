package com.iruda.ecomm.di

import androidx.room.Room
import com.iruda.ecomm.data.auth.database.AuthDao
import com.iruda.ecomm.data.auth.mappers.AuthMapper
import com.iruda.ecomm.data.auth.network.AuthApiFactory
import com.iruda.ecomm.data.auth.repositories.AuthRepositoryImpl
import com.iruda.ecomm.data.cart.database.CartDao
import com.iruda.ecomm.data.cart.mappers.CartMapper
import com.iruda.ecomm.data.cart.network.CartApiFactory
import com.iruda.ecomm.data.cart.repositories.CartRepositoryImpl
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
import com.iruda.ecomm.domain.auth.repositories.AuthRepository
import com.iruda.ecomm.domain.cart.repositories.CartRepository
import com.iruda.ecomm.domain.category.repositories.CategoryRepository
import com.iruda.ecomm.domain.product.repositories.ProductRepository
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val dataModule = module {

    // Mappers
    single { ProductMapper() }
    single { CategoryMapper() }
    single { CartMapper() }
    single { AuthMapper() }

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

    single<CartDao> {
        val database = get<AppDatabase>()
        database.cartDao()
    }

    single<AuthDao> {
        val database = get<AppDatabase>()
        database.authDao()
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

    single<CartRepository> {
        CartRepositoryImpl(
            cartDao = get(),
            factory = get(),
            mapper = get()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            authDao = get(),
            factory = get(),
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

    single<CartApiFactory> {
        CartApiFactory
    }

    single<AuthApiFactory> {
        AuthApiFactory
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