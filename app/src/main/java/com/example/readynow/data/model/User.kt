package com.example.readynow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDate


@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("UserName")
    var userName: String,

    @SerializedName("UserRole")
    var userRole: UserRole,
)

enum class UserRole{
    ATTENDEE,
    DEEJAY
}

