package com.example.readynow.domain.usecase

import com.example.readynow.data.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String, deviceId: String? = null, rememberMe: Boolean = false): Result<String> {
        return authRepository.signIn(username, password, deviceId, rememberMe)
    }
}