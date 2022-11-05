package com.iruda.ecomm.presentation.category.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.iruda.ecomm.data.product.repositories.ProductRepositoryImpl
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.domain.product.usecases.GetProductListInCategoryUseCase

class ProductsInCategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepositoryImpl(application)

    private val getProductListInCategoryUseCase = GetProductListInCategoryUseCase(repository)

    suspend fun getProductsInCategory(categoryName: String): LiveData<List<Product>> =
        getProductListInCategoryUseCase(categoryName = categoryName)
}