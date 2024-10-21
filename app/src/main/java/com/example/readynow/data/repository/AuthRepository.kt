package com.example.readynow.data.repository

import com.example.readynow.data.model.SignInRequest
import com.example.readynow.data.network.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun signIn(username: String, password: String, deviceId: String? = null, rememberMe: Boolean = false): Result<String> {
        return try {
            val response = apiService.signIn(SignInRequest(username, password, deviceId, rememberMe))
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.status == "success") {
                    Result.success(body.token ?: "")
                } else {
                    Result.failure(Exception(body?.message ?: "Unknown error"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}