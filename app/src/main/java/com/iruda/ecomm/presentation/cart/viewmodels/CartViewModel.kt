package com.iruda.ecomm.presentation.cart.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.iruda.ecomm.domain.cart.usecases.GetCartListUseCase
import com.iruda.ecomm.domain.cart.usecases.GetCartUseCase
import com.iruda.ecomm.domain.cart.usecases.LoadCartDataUseCase
import kotlinx.coroutines.launch

class CartViewModel(
    application: Application,
    private val getCartUseCase: GetCartUseCase,
    private val getCartListUseCase: GetCartListUseCase,
    private val loadCartDataUseCase: LoadCartDataUseCase,
) : AndroidViewModel(application) {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    init {
        loadData()
    }

    val cart = getCartUseCase()

    val cartProductList = _searchQuery.switchMap {
        if (it.isNullOrEmpty()) {
            getCartListUseCase(searchQuery = EMPTY_SEARCH)
        } else {
            getCartListUseCase(searchQuery = it)
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            loadCartDataUseCase(userId = USER_ID_TEMPLATE)
        }
    }

    fun postSearch(query: String) {
        _searchQuery.value = query
    }

    companion object {

        // TODO: Change userId to id when user authorizes

        private const val USER_ID_TEMPLATE = 5
        private const val EMPTY_SEARCH = ""
    }
}