package com.example.hpoke.core.ui.palette

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.kmpalette.palette.graphics.Palette

@Composable
fun Palette?.paletteBackgroundColor(): State<Color> {
    val defaultBackground = MaterialTheme.colorScheme.surface

    return remember(this) {
        derivedStateOf {
            this?.dominantSwatch?.rgb
                ?.let(::Color)
                ?: defaultBackground
        }
    }
}

@Composable
fun Palette?.paletteTextColor(): State<Color> {
    val defaultText = MaterialTheme.colorScheme.onSurface

    return remember(this) {
        derivedStateOf {
            this?.dominantSwatch?.titleTextColor
                ?.let(::Color)
                ?: defaultText
        }
    }
}
