package com.example.readynow.data.model

import androidx.annotation.DrawableRes


data class DrawableSongCard(
    @DrawableRes val drawable: Int,
    val song: Song)
