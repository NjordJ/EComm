package com.iruda.ecomm.data.home.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.iruda.ecomm.data.global.AppDatabase
import com.iruda.ecomm.data.home.mappers.ProductMapper
import com.iruda.ecomm.data.home.network.ProductApiFactory
import kotlinx.coroutines.delay

class RefreshProductsWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val productDao = AppDatabase.getInstance(context).productDao()
    private val apiService = ProductApiFactory.apiService

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val products = apiService.getAllProducts()
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