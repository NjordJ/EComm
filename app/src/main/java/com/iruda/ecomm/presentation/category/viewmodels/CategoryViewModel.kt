package com.iruda.ecomm.presentation.category.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iruda.ecomm.data.category.repositories.CategoryRepositoryImpl
import com.iruda.ecomm.domain.category.usecases.GetCategoryListUseCase
import com.iruda.ecomm.domain.category.usecases.GetCategoryUseCase
import com.iruda.ecomm.domain.category.usecases.LoadCategoryDataUseCase

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CategoryRepositoryImpl(application)

    private val getCategoryUseCase = GetCategoryUseCase(repository)
    private val getCategoryListUseCase = GetCategoryListUseCase(repository)
    private val loadCategoryDataUseCase = LoadCategoryDataUseCase(repository)

    val categoryList = getCategoryListUseCase()

    init {
        loadCategoryDataUseCase()
    }

    fun getDetailCategoryInfo(name: String) = getCategoryUseCase(name = name)

}