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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.widget.SimpleButton

@ExperimentalComposeUiApi
@Composable
fun LogInScreen(onLogInClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Content(onLogInClick)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun Content(
    onLogInClick: () -> Unit
) {
    val passwordFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Title(Modifier.align(Alignment.CenterHorizontally))

        Spacer(Modifier.height(16.dp))

        EmailTextField(passwordFocusRequester)

        Spacer(Modifier.height(8.dp))

        PasswordTextField(passwordFocusRequester)

        Spacer(Modifier.height(16.dp))

        LogInAgreementText()

        Spacer(Modifier.height(16.dp))

        CreateAccountButton(onLogInClick)
    }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.log_in_title_text),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
    )
}

@Composable
private fun EmailTextField(passwordFocusRequester: FocusRequester) {
    var email by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = { email = it },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onPrimary,
        ),
        maxLines = 1,
        placeholder = {
            Text(
                text = stringResource(R.string.log_in_email_hint),
                color = MaterialTheme.colors.onSurface
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                passwordFocusRequester.requestFocus()
            }
        )
    )
}

@ExperimentalComposeUiApi
@Composable
private fun PasswordTextField(focusRequester: FocusRequester) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var password by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
        value = password,
        onValueChange = { password = it },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onPrimary,
        ),
        maxLines = 1,
        placeholder = {
            Text(
                text = stringResource(R.string.log_in_password_hint),
                color = MaterialTheme.colors.onSurface
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hideSoftwareKeyboard()
            }
        )
    )
}

@Composable
private fun LogInAgreementText() {
    val logInAgreementText = getLogInAgreementText()

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = logInAgreementText,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun getLogInAgreementText() = buildAnnotatedString {
    val logInTermsOfUseText = stringResource(R.string.log_in_terms_of_use_text)

    val (termsOfUseStartIndex, termsOfUseEndIndex) =
        logInTermsOfUseText.indexOfStartEnd(stringResource(R.string.terms_of_use))

    val (privacyPolicyStartIndex, privacyPolicyEndIndex) =
        logInTermsOfUseText.indexOfStartEnd(stringResource(R.string.privacy_policy))

    append(logInTermsOfUseText)
    addUnderlineStyle(termsOfUseStartIndex, termsOfUseEndIndex)
    addUnderlineStyle(privacyPolicyStartIndex, privacyPolicyEndIndex)
}

private fun String.indexOfStartEnd(text: String): Pair<Int, Int> {
    val index = kotlin.math.max(this.indexOf(text), 0)
    return Pair(index, index + text.length)
}

private fun AnnotatedString.Builder.addUnderlineStyle(start: Int, end: Int) {
    addStyle(
        style = SpanStyle(textDecoration = TextDecoration.Underline),
        start = start,
        end = end,
    )
}

@Composable
private fun CreateAccountButton(onLogInClick: () -> Unit) {
    SimpleButton(
        text = stringResource(R.string.log_in),
        onClick = onLogInClick
    )
}

@ExperimentalComposeUiApi
@Preview("Log In Screen Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LogInScreenLightPreview() {
    MyTheme {
        LogInScreen { }
    }
}

@ExperimentalComposeUiApi
@Preview("Log In Screen Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LogInScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        LogInScreen { }
    }
}
