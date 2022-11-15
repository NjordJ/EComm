package com.iruda.ecomm.presentation.splashscreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iruda.ecomm.domain.cart.usecases.LoadCartDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    loadCartDataUseCase: LoadCartDataUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            loadCartDataUseCase()
            _isLoading.value = false
        }
    }
}