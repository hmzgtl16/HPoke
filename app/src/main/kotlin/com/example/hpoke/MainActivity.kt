package com.example.hpoke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.NavDisplay
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.navigation3.getEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
class MainActivity : ComponentActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HPokeTheme {
                NavDisplay(
                    backStack = navigator.backStack,
                    modifier = Modifier,
                    onBack = navigator::navigateUp,
                    entryProvider = getEntryProvider()
                )
            }
        }
    }
}