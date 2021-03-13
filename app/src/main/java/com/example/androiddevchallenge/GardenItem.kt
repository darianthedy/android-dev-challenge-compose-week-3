package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

data class GardenItem(
    @DrawableRes val image: Int,
    val title: String,
    val subtitle: String,
)
