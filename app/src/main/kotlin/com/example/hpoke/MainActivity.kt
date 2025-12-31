/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hpoke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.navigation.Navigator
import com.example.hpoke.navigation.NavHost
import org.koin.android.ext.android.inject
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.component.KoinComponent

@OptIn(KoinExperimentalAPI::class)
class MainActivity :
    ComponentActivity(),
    KoinComponent {
    private val navigator by inject<Navigator>()

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HPokeTheme {
                Scaffold(
                    modifier = Modifier,
                ) {
                    NavHost(
                        modifier = Modifier,
                        backStack = navigator.backStack,
                        onBack = navigator::navigateUp,
                    )
                }
            }
        }
    }
}
