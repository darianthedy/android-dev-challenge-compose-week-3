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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screens.HomeScreen
import com.example.androiddevchallenge.ui.screens.LogInScreen
import com.example.androiddevchallenge.ui.screens.WelcomeScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalComposeUiApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                window.statusBarColor = MaterialTheme.colors.background.toArgb()
                window.navigationBarColor = MaterialTheme.colors.background.toArgb()

                MyApp(themes, gardens)
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun MyApp(themes: List<ThemeItem>, gardens: List<GardenItem>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NAV_HOME) {
        composable(NAV_WELCOME_SCREEN) { WelcomeScreen { toLogInPage(navController) } }
        composable(NAV_LOG_IN_SCREEN) { LogInScreen { toHomePage(navController) } }
        composable(NAV_HOME) { HomeScreen(themes, gardens) }
    }
}

private fun toLogInPage(navController: NavController) {
    navController.navigate(NAV_LOG_IN_SCREEN)
}

private fun toHomePage(navController: NavController) {
    navController.navigate(NAV_HOME)
}