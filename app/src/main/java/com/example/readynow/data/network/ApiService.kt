package com.example.readynow.data.network

import com.example.readynow.data.model.SignInRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signin")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}

data class SignInResponse(
    val status: String,
    val token: String?,
    val message: String?
)