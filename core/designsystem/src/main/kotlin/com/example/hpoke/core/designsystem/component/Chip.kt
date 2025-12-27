package com.example.hpoke.core.designsystem.component

import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.hpoke.core.designsystem.theme.HPokeTheme

@Composable
fun HPokeChip(
    modifier: Modifier = Modifier,
    label: String,
    colors: ChipColors = AssistChipDefaults.elevatedAssistChipColors(),
) {

    ElevatedAssistChip(
        onClick = {},
        label = { Text(text = label) },
        colors = colors,
        modifier = modifier
    )
}

@PreviewLightDark
@Composable
private fun HPokeChipPreview() {
    HPokeTheme {
        HPokeChip(label = "Pikachu", colors = AssistChipDefaults.elevatedAssistChipColors())
    }
}