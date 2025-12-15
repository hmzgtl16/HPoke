package com.example.hpoke.core.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.hpoke.core.designsystem.theme.HPokeTheme

@Composable
fun HPokeCircularProgressIndicator(
    color: Color = ProgressIndicatorDefaults.circularColor,
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        color = color,
        modifier = modifier
    )
}

@PreviewLightDark
@Composable
fun HPokeCircularProgressIndicatorPreview() {

    HPokeTheme {
        HPokeCircularProgressIndicator()
    }
}