package com.iruda.ecomm.data.product.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.iruda.ecomm.data.product.database.ProductDao
import com.iruda.ecomm.data.product.network.ProductApiFactory
import kotlinx.coroutines.delay

class RefreshProductsWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val productDao: ProductDao,
    private val factory: ProductApiFactory
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val productRaw = factory.apiService.getAllProducts()
                val products = productRaw.products
                productDao.insertProductList(products)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {

        const val NAME = "RefreshProductWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshProductsWorker>().build()
        }
    }
}