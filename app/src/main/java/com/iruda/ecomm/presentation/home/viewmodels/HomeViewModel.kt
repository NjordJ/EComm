package com.iruda.ecomm.presentation.home.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.usecases.GetProductListUseCase
import com.iruda.ecomm.domain.product.usecases.GetProductUseCase
import com.iruda.ecomm.domain.product.usecases.LoadProductDataUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepositoryImpl(application)

    private val _searchQuery = MutableLiveData<String>(EMPTY_SEARCH)
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val getProductUseCase = GetProductUseCase(repository)
    private val getProductListUseCase = GetProductListUseCase(repository)
    private val loadProductDataUseCase = LoadProductDataUseCase(repository)

    var productList: LiveData<List<Product>> =
        getProductListUseCase(searchQuery = _searchQuery.value.toString())

    init {
        loadProductDataUseCase()
    }

    fun postSearch(value: String) {
        Log.d("SearchView", value)
        _searchQuery.value = value
    }

    fun updateProductList(query: String) {
        productList = getProductListUseCase(searchQuery = query)
    }

    fun getDetailProductInfo(id: Int) = getProductUseCase(id = id)

    companion object {

        private const val EMPTY_SEARCH = ""
    }
}