package com.iruda.ecomm.presentation.category.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.iruda.ecomm.data.category.database.CategoryDao
import com.iruda.ecomm.data.category.repositories.CategoryRepositoryImpl
import com.iruda.ecomm.domain.category.usecases.GetCategoryListUseCase
import com.iruda.ecomm.domain.category.usecases.LoadCategoryDataUseCase

class CategoryViewModel(application: Application, categoryDao: CategoryDao) :
    AndroidViewModel(application) {

    private val repository = CategoryRepositoryImpl(application, categoryDao)

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val getCategoryListUseCase = GetCategoryListUseCase(repository)
    private val loadCategoryDataUseCase = LoadCategoryDataUseCase(repository)

    val categoryList = _searchQuery.switchMap {
        if (it.isNullOrEmpty()) {
            getCategoryListUseCase(searchQuery = EMPTY_SEARCH)
        } else {
            getCategoryListUseCase(searchQuery = it)
        }
    }

    init {
        loadCategoryDataUseCase()
        _searchQuery.value = EMPTY_SEARCH
    }

    fun postSearch(query: String) {
        _searchQuery.value = query
    }

    companion object {

        private const val EMPTY_SEARCH = ""
    }

}