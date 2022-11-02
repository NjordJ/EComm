package com.iruda.ecomm.data.home.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.iruda.ecomm.data.global.AppDatabase
import com.iruda.ecomm.data.home.mappers.ProductMapper
import com.iruda.ecomm.domain.home.entities.Product
import com.iruda.ecomm.domain.home.repositories.ProductRepository

class ProductRepositoryImpl(
    private val application: Application
) : ProductRepository {

    private val productDao = AppDatabase.getInstance(application).productDao()
    private val mapper = ProductMapper()

    override fun getProductList(): LiveData<List<Product>> {
        return Transformations.map(productDao.getProductList()) {
            it.map { model ->
                mapper.mapModelToEntity(model = model)
            }
        }
    }

    override fun getProduct(id: Int): LiveData<Product> {
        return Transformations.map(productDao.getProduct(id = id)) {
            mapper.mapModelToEntity(it)
        }
    }


}