package com.example.readynow.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class User(
    @SerializedName("UserName")
    var userName: String,

    @SerializedName("UserRole")
    var userRole: UserRole,
)

enum class UserRole{
    ATTENDEE,
    DEEJAY
}

