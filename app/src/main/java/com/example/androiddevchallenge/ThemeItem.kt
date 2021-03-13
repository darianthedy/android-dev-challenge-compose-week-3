package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

data class ThemeItem(
    @DrawableRes val image: Int,
    val title: String,
)