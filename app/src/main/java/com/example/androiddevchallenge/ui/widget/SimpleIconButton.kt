package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SimpleIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    iconText: String = "",
    onClick: () -> Unit = { },
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector,
                contentDescription = iconText,
                modifier = Modifier.size(24.dp),
                tint = tint
            )
            Text(
                text = iconText,
                style = MaterialTheme.typography.caption,
                color = tint,
            )
        }
    }
}