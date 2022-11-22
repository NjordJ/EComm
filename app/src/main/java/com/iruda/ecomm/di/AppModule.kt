package com.iruda.ecomm.di

import com.iruda.ecomm.presentation.account.viewmodels.AccountViewModel
import com.iruda.ecomm.presentation.account.viewmodels.LoginViewModel
import com.iruda.ecomm.presentation.cart.viewmodels.CartViewModel
import com.iruda.ecomm.presentation.category.viewmodels.CategoryViewModel
import com.iruda.ecomm.presentation.category.viewmodels.ProductsInCategoryViewModel
import com.iruda.ecomm.presentation.home.viewmodels.HomeViewModel
import com.iruda.ecomm.presentation.splashscreen.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            loadCartDataUseCase = get()
        )
    }

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

    viewModel<CartViewModel> {
        CartViewModel(
            application = get(),
            getCartUseCase = get()
        )
    }

    viewModel<AccountViewModel> {
        AccountViewModel(
            application = get(),
            logOutUseCase = get(),
            getAuthResponseUseCase = get()
        )
    }

    viewModel<LoginViewModel> {
        LoginViewModel(
            application = get(),
            getAuthResponseUseCase = get(),
            authorizeWithEmailUseCase = get()
        )
    }
}