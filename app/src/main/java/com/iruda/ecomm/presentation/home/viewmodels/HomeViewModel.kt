package com.iruda.ecomm.presentation.home.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.product.usecases.GetProductListUseCase
import com.iruda.ecomm.domain.product.usecases.LoadProductDataUseCase

class HomeViewModel(application: Application, repository: ProductRepositoryImpl) :
    AndroidViewModel(application) {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val getProductListUseCase = GetProductListUseCase(repository)
    private val loadProductDataUseCase = LoadProductDataUseCase(repository)

    val productList = _searchQuery.switchMap {
        if (it.isNullOrEmpty()) {
            getProductListUseCase(searchQuery = EMPTY_SEARCH)
        } else {
            getProductListUseCase(searchQuery = it)
        }
    }

    init {
        loadProductDataUseCase()
        _searchQuery.value = EMPTY_SEARCH
    }

    fun postSearch(query: String) {
        _searchQuery.value = query
    }

    companion object {

        private const val EMPTY_SEARCH = ""
    }
}