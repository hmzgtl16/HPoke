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
        modifier = modifier,
    )
}

@PreviewLightDark
@Composable
private fun HPokeChipPreview() {
    HPokeTheme {
        HPokeChip(label = "Pikachu", colors = AssistChipDefaults.elevatedAssistChipColors())
    }
}
