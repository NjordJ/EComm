package com.iruda.ecomm.presentation.cart.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iruda.ecomm.domain.cart.usecases.GetCartUseCase

class CartViewModel(
    application: Application,
    private val getCartUseCase: GetCartUseCase
) : AndroidViewModel(application) {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    val cart = getCartUseCase()

    init {
        _searchQuery.value = EMPTY_SEARCH
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