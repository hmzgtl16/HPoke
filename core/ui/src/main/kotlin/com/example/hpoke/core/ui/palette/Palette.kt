package com.example.hpoke.core.ui.palette

import android.graphics.Bitmap
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette


@Composable
internal fun Palette?.paletteBackgroundColor(): State<Color> {
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
fun rememberPaletteState(value: Palette? = null): MutableState<Palette?> {
    return remember(value) {
        mutableStateOf(value = value)
    }
}

fun Bitmap.generatePalette(): Palette = Palette.from(this).generate()