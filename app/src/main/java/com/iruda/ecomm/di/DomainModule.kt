package com.iruda.ecomm.di

import com.iruda.ecomm.domain.auth.usecases.AuthorizeWithEmailUseCase
import com.iruda.ecomm.domain.auth.usecases.GetAuthResponseUseCase
import com.iruda.ecomm.domain.category.usecases.GetCategoryListUseCase
import com.iruda.ecomm.domain.category.usecases.LoadCategoryDataUseCase
import com.iruda.ecomm.domain.product.usecases.GetProductListInCategoryUseCase
import com.iruda.ecomm.domain.product.usecases.GetProductListUseCase
import com.iruda.ecomm.domain.product.usecases.LoadProductDataUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetProductListUseCase> {
        GetProductListUseCase(
            repository = get()
        )
    }

    factory<LoadProductDataUseCase> {
        LoadProductDataUseCase(
            repository = get()
        )
    }

    factory<GetProductListInCategoryUseCase> {
        GetProductListInCategoryUseCase(
            repository = get()
        )
    }

    factory<GetCategoryListUseCase> {
        GetCategoryListUseCase(
            repository = get()
        )
    }

    factory<LoadCategoryDataUseCase> {
        LoadCategoryDataUseCase(
            repository = get()
        )
    }

    factory<AuthorizeWithEmailUseCase> {
        AuthorizeWithEmailUseCase(
            repository = get()
        )
    }

    factory<GetAuthResponseUseCase> {
        GetAuthResponseUseCase(
            repository = get()
        )
    }
}