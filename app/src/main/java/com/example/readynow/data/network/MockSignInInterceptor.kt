package com.example.readynow.data.network

import com.example.readynow.data.model.SignInRequest
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection

class MockSignInInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.encodedPath.endsWith("/signin") && request.method == "POST") {
            val requestBody = request.body?.let { body ->
                val buffer = okio.Buffer()
                body.writeTo(buffer)
                buffer.readUtf8()
            }

            val gson = Gson()
            val signInRequest = gson.fromJson(requestBody, SignInRequest::class.java)

            val (responseCode, responseBody) = when (signInRequest.username) {
                "success@example.com" -> Pair(
                    HttpURLConnection.HTTP_OK,
                    """{"status": "success", "token": "mock_token_12345", "device_id": "${signInRequest.deviceId}"}"""
                )
                "failure@example.com" -> Pair(
                    HttpURLConnection.HTTP_UNAUTHORIZED,
                    """{"status": "failure", "message": "Invalid credentials"}"""
                )
                "error@example.com" -> Pair(
                    HttpURLConnection.HTTP_INTERNAL_ERROR,
                    """{"status": "error", "message": "Server error"}"""
                )
                else -> Pair(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    """{"status": "error", "message": "Invalid request"}"""
                )
            }

            return Response.Builder()
                .code(responseCode)
                .protocol(Protocol.HTTP_2)
                .message(responseBody)
                .body(responseBody.toResponseBody("application/json".toMediaTypeOrNull()))
                .request(request)
                .build()
        }

        return chain.proceed(request)
    }
}