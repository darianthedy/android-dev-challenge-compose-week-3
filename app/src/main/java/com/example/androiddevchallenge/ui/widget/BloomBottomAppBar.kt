/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.iconDark
import com.example.androiddevchallenge.ui.theme.iconLight

@Composable
fun BloomBottomAppBar() {
    val iconColor = if (isSystemInDarkTheme()) iconDark else iconLight

    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        SimpleIconButton(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.Home,
            tint = MaterialTheme.colors.onPrimary,
            iconText = stringResource(R.string.home)
        )
        SimpleIconButton(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.FavoriteBorder,
            tint = iconColor,
            iconText = stringResource(R.string.favorites)
        )
        SimpleIconButton(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.AccountCircle,
            tint = iconColor,
            iconText = stringResource(R.string.profile)
        )
        SimpleIconButton(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.ShoppingCart,
            tint = iconColor,
            iconText = stringResource(R.string.cart)
        )
    }
}
