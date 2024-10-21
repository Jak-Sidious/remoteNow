package com.example.readynow.data.model

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("device_id")
    val deviceId: String? = null,

    @SerializedName("remember_me")
    val rememberMe: Boolean = false
)