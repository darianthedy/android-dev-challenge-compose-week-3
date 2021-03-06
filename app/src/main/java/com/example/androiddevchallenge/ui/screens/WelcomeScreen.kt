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
package com.example.androiddevchallenge.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.white
import com.example.androiddevchallenge.ui.widget.SimpleButton

private class WelcomeScreenRes(
    @DrawableRes val logo: Int,
    @DrawableRes val welcomeBg: Int,
    @DrawableRes val welcomeIllos: Int,
    val logInColor: Color,
)

@Composable
fun WelcomeScreen(onLogInClick: () -> Unit) {
    val welcomeScreenRes = getWelcomeScreenRes()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        BackgroundImage(welcomeScreenRes)
        Content(welcomeScreenRes, onLogInClick)
    }
}

@Composable
private fun getWelcomeScreenRes(): WelcomeScreenRes =
    if (isSystemInDarkTheme()) {
        WelcomeScreenRes(
            logo = R.drawable.dark_logo,
            welcomeBg = R.drawable.dark_welcome_bg,
            welcomeIllos = R.drawable.dark_welcome_illos,
            logInColor = white,
        )
    } else {
        WelcomeScreenRes(
            logo = R.drawable.light_logo,
            welcomeBg = R.drawable.light_welcome_bg,
            welcomeIllos = R.drawable.light_welcome_illos,
            logInColor = pink900,
        )
    }

@Composable
private fun BackgroundImage(welcomeScreenRes: WelcomeScreenRes) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(welcomeScreenRes.welcomeBg),
        contentDescription = null,
    )
}

@Composable
private fun Content(
    welcomeScreenRes: WelcomeScreenRes,
    onLogInClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WelcomeImage(
            Modifier
                .padding(top = 64.dp, start = 88.dp, bottom = 48.dp)
                .align(Alignment.End),
            welcomeScreenRes
        )

        LogoText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            welcomeScreenRes
        )

        Spacer(Modifier.height(12.dp))

        SubtitleText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(Modifier.height(40.dp))

        CreateAccountButton()

        Spacer(Modifier.height(16.dp))

        LogInText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            welcomeScreenRes = welcomeScreenRes,
            onLogInClick = onLogInClick,
        )
    }
}

@Composable
private fun WelcomeImage(
    modifier: Modifier = Modifier,
    welcomeScreenRes: WelcomeScreenRes
) {
    Image(
        modifier = modifier.height(280.dp),
        painter = painterResource(welcomeScreenRes.welcomeIllos),
        contentDescription = null,
        contentScale = ContentScale.None,
        alignment = Alignment.TopStart,
    )
}

@Composable
private fun LogoText(
    modifier: Modifier = Modifier,
    welcomeScreenRes: WelcomeScreenRes
) {
    Image(
        modifier = modifier,
        painter = painterResource(welcomeScreenRes.logo),
        contentDescription = stringResource(R.string.welcome_logo_content_description),
    )
}

@Composable
private fun SubtitleText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.welcome_subtitle_text),
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onPrimary,
    )
}

@Composable
private fun CreateAccountButton() {
    SimpleButton(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        text = stringResource(R.string.welcome_subtitle_create_account_text),
        onClick = { }
    )
}

@Composable
private fun LogInText(
    modifier: Modifier = Modifier,
    welcomeScreenRes: WelcomeScreenRes,
    onLogInClick: () -> Unit,
) {
    Text(
        modifier = modifier.clickable(onClick = onLogInClick),
        text = stringResource(R.string.log_in),
        style = MaterialTheme.typography.button,
        color = welcomeScreenRes.logInColor,
    )
}

@Preview("Welcome Screen Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeScreenLightPreview() {
    MyTheme {
        WelcomeScreen { }
    }
}

@Preview("Welcome Screen Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        WelcomeScreen { }
    }
}
