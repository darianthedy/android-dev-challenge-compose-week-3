package com.example.androiddevchallenge

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.white

private class WelcomeScreenRes(
    @DrawableRes val logo: Int,
    @DrawableRes val welcomeBg: Int,
    @DrawableRes val welcomeIllos: Int,
    val logInColor: Color,
)

@Composable
fun WelcomeScreen(onLogInClick: () -> Unit) {
    val welcomeScreenRes = getWelcomeScreenRes()

    Surface(color = MaterialTheme.colors.primary) {
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
        painter = painterResource(id = welcomeScreenRes.welcomeBg),
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
            Modifier.padding(top = 64.dp, start = 88.dp, bottom = 48.dp).align(Alignment.End),
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
        painter = painterResource(id = welcomeScreenRes.welcomeIllos),
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
        painter = painterResource(id = welcomeScreenRes.logo),
        contentDescription = "Bloom",
    )
}

@Composable
private fun SubtitleText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "Beautiful home garden solutions",
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onPrimary,
    )
}

@Composable
private fun CreateAccountButton() {
    Button(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .height(48.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ),
        onClick = { }
    ) {
        Text(
            "Create account",
            style = MaterialTheme.typography.button,
        )
    }
}

@Composable
private fun LogInText(
    modifier: Modifier = Modifier,
    welcomeScreenRes: WelcomeScreenRes,
    onLogInClick: () -> Unit,
) {
    Text(
        modifier = modifier.clickable { onLogInClick() },
        text = "Log in",
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