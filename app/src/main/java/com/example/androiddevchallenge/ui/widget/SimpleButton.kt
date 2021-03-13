package com.example.androiddevchallenge.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun SimpleButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(48.dp).fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
        )
    }
}

@Preview("Simple Button Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun SimpleButtonLightPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                SimpleButton(text = "Test button", onClick = { })
            }
        }
    }
}

@Preview("Simple Button Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun SimpleButtonDarkPreview() {
    MyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                SimpleButton(text = "Test button", onClick = { })
            }
        }
    }
}