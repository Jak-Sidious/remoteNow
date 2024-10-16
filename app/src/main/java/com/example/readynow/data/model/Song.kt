package com.example.readynow.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate


data class Song(
    @SerializedName("Title")
    var title: String,

    @SerializedName("DateAdded")
    var dateAdded: LocalDate,

    @SerializedName("Played")
    var played: Boolean
)


