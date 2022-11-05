package com.iruda.ecomm.presentation.home.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.product.usecases.GetProductListUseCase
import com.iruda.ecomm.domain.product.usecases.GetProductUseCase
import com.iruda.ecomm.domain.product.usecases.LoadProductDataUseCase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepositoryImpl(application)

    private val getProductUseCase = GetProductUseCase(repository)
    private val getProductListUseCase = GetProductListUseCase(repository)
    private val loadProductDataUseCase = LoadProductDataUseCase(repository)

    val productList = getProductListUseCase()

    init {
        loadProductDataUseCase()
    }

    fun getDetailProductInfo(id: Int) = getProductUseCase(id = id)

}