package com.iruda.ecomm.data.product.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.iruda.ecomm.data.product.database.ProductDao
import com.iruda.ecomm.data.product.mappers.ProductMapper
import com.iruda.ecomm.data.product.workers.RefreshProductsWorker
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.repositories.ProductRepository

class ProductRepositoryImpl(
    private val application: Application,
    private val productDao: ProductDao,
    private val mapper: ProductMapper
) : ProductRepository {

    override fun getProductList(searchQuery: String): LiveData<List<Product>> {
        return Transformations.map(
            productDao.getProductList(
                searchQuery = searchQuery
            )
        ) {
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

    override fun getProductListInCategory(
        categoryName: String, searchQuery: String
    ): LiveData<List<Product>> {
        val category: String = categoryName.replaceFirstChar { it.lowercase() }
        return Transformations.map(
            productDao.getProductListInCategory(
                categoryName = category, searchQuery = searchQuery
            )
        ) {
            it.map { model ->
                mapper.mapModelToEntity(model = model)
            }
        }
    }
}