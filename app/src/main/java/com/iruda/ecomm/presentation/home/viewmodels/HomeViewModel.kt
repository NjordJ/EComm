package com.iruda.ecomm.presentation.home.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.iruda.ecomm.data.home.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.home.usecases.GetProductListUseCase
import com.iruda.ecomm.domain.home.usecases.GetProductUseCase
import com.iruda.ecomm.domain.home.usecases.LoadProductDataUseCase

class HomeViewModel(application: Application) : ViewModel() {

    private val repository = ProductRepositoryImpl(application)

    private val getProductUseCase = GetProductUseCase(repository)
    private val getProductListUseCase = GetProductListUseCase(repository)
    private val loadProductDataUseCase = LoadProductDataUseCase(repository)

    val productList = getProductListUseCase()

    init {
        loadProductDataUseCase()
    }

    fun getDetailProductInfo(id: Int) = getProductUseCase(id)

}