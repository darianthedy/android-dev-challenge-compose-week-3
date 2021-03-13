package com.example.androiddevchallenge

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LogInScreen(onLogInClick: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {

    }
}

@Preview("Log In Screen Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LogInScreenLightPreview() {
    MyTheme {
        WelcomeScreen { }
    }
}

@Preview("Log In Screen Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LogInScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        WelcomeScreen { }
    }
}