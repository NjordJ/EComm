package com.iruda.ecomm.di

import com.iruda.ecomm.presentation.account.viewmodels.AccountViewModel
import com.iruda.ecomm.presentation.category.viewmodels.CategoryViewModel
import com.iruda.ecomm.presentation.category.viewmodels.ProductsInCategoryViewModel
import com.iruda.ecomm.presentation.home.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            application = get(),
            getProductListUseCase = get(),
            loadProductDataUseCase = get()
        )
    }

    viewModel<CategoryViewModel> {
        CategoryViewModel(
            application = get(),
            getCategoryListUseCase = get(),
            loadCategoryDataUseCase = get()
        )
    }

    viewModel<ProductsInCategoryViewModel> {
        ProductsInCategoryViewModel(
            application = get(),
            getProductListInCategoryUseCase = get()
        )
    }

    viewModel<AccountViewModel> {
        AccountViewModel(
            application = get(),
            authorizeWithEmailUseCase = get(),
            getAuthResponseUseCase = get()
        )
    }
}