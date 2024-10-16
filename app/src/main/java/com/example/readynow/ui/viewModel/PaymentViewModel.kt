package com.example.readynow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readynow.data.model.PaymentResult
import com.example.readynow.data.model.SongRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<PaymentUiState>(PaymentUiState.Idle)
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    fun processPayment(songRequest: SongRequest) {
        viewModelScope.launch {
            _uiState.value = PaymentUiState.Loading
            val result = processPayment(songRequest)
            when (val result = processPayment(songRequest)) {
//                is Success -> {
//                    _uiState.value = PaymentUiState.Success(result.data)
//                }
//                is Error -> {
//                    _uiState.value = PaymentUiState.Error(result.exception.message ?: "Unknown error occurred")
//                }
//                is Loading -> {
//                    // This state is handled in the processPayment function
//                }
            }
        }
    }

    fun retryPayment(songRequest: SongRequest) {
        processPayment(songRequest)
    }

    fun dismissError() {
        _uiState.value = PaymentUiState.Idle
    }
}

sealed class PaymentUiState {
    object Idle : PaymentUiState()
    object Loading : PaymentUiState()
    data class Success(val paymentResult: PaymentResult) : PaymentUiState()
    data class Error(val errorMessage: String) : PaymentUiState()
}