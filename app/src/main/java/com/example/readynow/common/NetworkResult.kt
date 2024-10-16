package com.example.readynow.common

import com.example.readynow.data.model.PaymentResult
import com.example.readynow.data.model.SongRequest
import kotlinx.coroutines.delay
import kotlin.random.Random

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}

class PaymentException(message: String) : Exception(message)

suspend fun processPayment(songRequest: SongRequest): NetworkResult<PaymentResult> {
    return try {
        // Simulating network call
        delay(2000)
        if (Random.nextBoolean()) {
            throw PaymentException("Network connection lost during payment processing")
        }
        NetworkResult.Success(
            PaymentResult(
                message = "Payment successful for ${songRequest.title} by ${songRequest.artist}",
                amount = songRequest.price
            )
        )
    } catch (e: Exception) {
        NetworkResult.Error(e)
    }
}