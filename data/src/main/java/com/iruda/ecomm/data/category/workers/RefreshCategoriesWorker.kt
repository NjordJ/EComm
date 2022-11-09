package com.iruda.ecomm.data.category.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.mappers.CategoryMapper
import com.iruda.ecomm.data.category.models.CategoryModel
import com.iruda.ecomm.data.category.network.CategoryApiFactory
import kotlinx.coroutines.delay

class RefreshCategoriesWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val categoryDao: CategoryDao,
    private val mapper: CategoryMapper,
    private val factory: CategoryApiFactory
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {

        while (true) {
            try {
                val rawCategories = factory.apiService.getAllCategories()
                val categories: MutableList<CategoryModel> = mutableListOf()
                for (category in rawCategories) {
                    categories.add(
                        mapper.mapStringToModel(category.replaceFirstChar {
                            it.uppercase()
                        })
                    )
                }
                categoryDao.insertCategoryList(categories)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {

        const val NAME = "RefreshCategoryWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshCategoriesWorker>().build()
        }
    }
}