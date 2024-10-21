package com.example.readynow.ui.screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readynow.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState: StateFlow<SignInState> = _signInState

    fun signIn(username: String, password: String, deviceId: String? = null, rememberMe: Boolean = false) {
        viewModelScope.launch {
            _signInState.value = SignInState.Loading
            val result = signInUseCase(username, password, deviceId, rememberMe)
            _signInState.value = when {
                result.isSuccess -> SignInState.Success(result.getOrNull()!!)
                else -> SignInState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}

sealed class SignInState {
    object Idle : SignInState()
    object Loading : SignInState()
    data class Success(val token: String) : SignInState()
    data class Error(val message: String) : SignInState()
}