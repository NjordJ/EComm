package com.iruda.ecomm.data.category.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.iruda.ecomm.data.category.mappers.CategoryMapper
import com.iruda.ecomm.data.category.workers.RefreshCategoriesWorker
import com.iruda.ecomm.data.global.AppDatabase
import com.iruda.ecomm.domain.category.entities.Category
import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class CategoryRepositoryImpl(
    private val application: Application
) : CategoryRepository {

    private val categoryDao = AppDatabase.getInstance(application).categoryDao()
    private val mapper = CategoryMapper()

    override fun getCategoryList(): LiveData<List<Category>> {
        return Transformations.map(categoryDao.getCategoryList()) {
            it.map { model ->
                mapper.mapModelToEntity(model = model)
            }
        }
    }

    override fun getCategory(name: String): LiveData<Category> {
        return Transformations.map(categoryDao.getCategory(name = name)) {
            mapper.mapModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshCategoriesWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshCategoriesWorker.makeRequest()
        )
    }
}