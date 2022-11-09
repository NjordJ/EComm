package com.iruda.ecomm.data.category.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.mappers.CategoryMapper
import com.iruda.ecomm.data.category.workers.RefreshCategoriesWorker
import com.iruda.ecomm.domain.category.entities.Category
import com.iruda.ecomm.domain.category.repositories.CategoryRepository

class CategoryRepositoryImpl(
    private val application: Application,
    private val categoryDao: CategoryDao
) : CategoryRepository {

    private val mapper = CategoryMapper()

    override fun getCategoryList(searchQuery: String): LiveData<List<Category>> {
        return Transformations.map(categoryDao.getCategoryList(searchQuery = searchQuery)) {
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