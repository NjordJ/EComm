package com.iruda.ecomm.presentation.category.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.usecases.GetProductListInCategoryUseCase

class ProductsInCategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepositoryImpl(application)

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val getProductListInCategoryUseCase = GetProductListInCategoryUseCase(repository)

    init {
        _searchQuery.value = EMPTY_SEARCH
    }

    fun postSearch(query: String) {
        _searchQuery.value = query
    }

    fun getProductsInCategory(categoryName: String): LiveData<List<Product>> =
        _searchQuery.switchMap {
            if (it.isNullOrEmpty()) {
                getProductListInCategoryUseCase(
                    categoryName = categoryName,
                    searchQuery = EMPTY_SEARCH
                )
            } else {
                getProductListInCategoryUseCase(
                    categoryName = categoryName,
                    searchQuery = it
                )
            }
        }

    companion object {

        private const val EMPTY_SEARCH = ""
    }
}