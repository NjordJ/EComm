package com.iruda.ecomm.data.product.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.iruda.ecomm.data.global.AppDatabase
import com.iruda.ecomm.data.product.mappers.ProductMapper
import com.iruda.ecomm.data.product.network.ProductApiFactory
import com.iruda.ecomm.data.product.workers.RefreshProductsWorker
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.repositories.ProductRepository

class ProductRepositoryImpl(
    private val application: Application
) : ProductRepository {

    private val productDao = AppDatabase.getInstance(application).productDao()
    private val apiService = ProductApiFactory.apiService
    private val mapper = ProductMapper()

    override fun getProductList(): LiveData<List<Product>> {
        return Transformations.map(productDao.getProductList()) {
            it.map { model ->
                mapper.mapModelToEntity(model = model)
            }
        }
    }

    override fun getProduct(id: Int): LiveData<Product> {
        return Transformations.map(productDao.getProduct(id = id)) {
            mapper.mapModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshProductsWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshProductsWorker.makeRequest()
        )
    }

    override suspend fun getProductListInCategory(categoryName: String): LiveData<List<Product>> {
        //val products = apiService.getProductListInCategory(categoryName)
        val category: String = categoryName.replaceFirstChar { it.lowercase() }
        return Transformations.map(productDao.getProductListInCategory(categoryName = category)) {
            it.map { model ->
                mapper.mapModelToEntity(model = model)
            }
        }
    }
}