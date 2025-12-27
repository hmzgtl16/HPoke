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
class MainActivity : ComponentActivity(), KoinComponent {

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
                        onBack = navigator::navigateUp
                    )
                }
            }
        }
    }
}