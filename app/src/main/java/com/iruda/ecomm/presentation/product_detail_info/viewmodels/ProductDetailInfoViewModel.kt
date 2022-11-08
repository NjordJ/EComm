package com.iruda.ecomm.presentation.product_detail_info.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.usecases.GetProductListInCategoryUseCase
import com.iruda.ecomm.domain.product.usecases.GetProductUseCase

class ProductDetailInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepositoryImpl(application)

    private val getProductUseCase = GetProductUseCase(repository)

    fun getDetailProductInfo(id: Int) = getProductUseCase(id = id)
}