package com.example.readynow.data.model

import java.util.UUID

data class SongRequest(
    val title: String,
    val artist: String,
    val requesterId: String = UUID.randomUUID().toString(),
    val price: Double = 1.99 // Default price, could be variable based on song or user type
)

