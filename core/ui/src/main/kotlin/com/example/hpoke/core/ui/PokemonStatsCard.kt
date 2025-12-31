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

package com.example.hpoke.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.model.Stat
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider

@Composable
fun PokemonStats(
    stats: List<Stat>,
    modifier: Modifier = Modifier,
    steps: Int = 7,
    chartHeight: Dp = 100.dp,
    gap: Dp = 3.dp,
) {
    val statMap = stats.asStatMap()

    ElevatedCard(
        modifier = modifier,
        colors =
            CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Top,
                ),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.core_ui_stats),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(height = chartHeight),
                    horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
                ) {
                    PokemonStat.entries.forEach {
                        StatBar(
                            value = statMap[it] ?: 0,
                            max = it.max,
                            steps = steps,
                            modifier =
                                Modifier
                                    .weight(weight = 1f)
                                    .fillMaxHeight(),
                            gap = gap,
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(
                            space = 6.dp,
                            alignment = Alignment.CenterHorizontally,
                        ),
                ) {
                    PokemonStat.entries.forEach {
                        Text(
                            text = stringResource(id = it.labelRes),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .weight(weight = 1f),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatBar(
    value: Int,
    max: Int,
    steps: Int,
    modifier: Modifier = Modifier,
    gap: Dp = 3.dp,
    cellColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
    fillGradientTop: Color = MaterialTheme.colorScheme.primaryContainer,
    fillGradientBottom: Color = MaterialTheme.colorScheme.primary,
) {
    Canvas(modifier = modifier) {
        val gapPx = gap.toPx()
        val cellHeight = (size.height - gapPx * (steps - 1)) / steps
        val cellWidth = size.width
        val radius = CornerRadius(x = 2.dp.toPx(), y = 2.dp.toPx())

        val filledSteps =
            ((value.coerceIn(0, max).toFloat() / max) * steps)
                .toInt()
                .coerceIn(minimumValue = 0, maximumValue = steps)

        // Gradient brush (bottom → top)
        val gradient =
            Brush.verticalGradient(
                colors = listOf(fillGradientBottom, fillGradientTop),
                startY = size.height,
                endY = 0f,
            )

        // base grid
        for (row in 0 until steps) {
            val y = row * (cellHeight + gapPx)
            drawRoundRect(
                color = cellColor,
                topLeft = Offset(x = 0f, y = y),
                size = Size(width = cellWidth, height = cellHeight),
                cornerRadius = radius,
            )
        }

        // fill (bottom-up)
        for (i in 0 until filledSteps) {
            val rowFromBottom = steps - 1 - i
            val y = rowFromBottom * (cellHeight + gapPx)
            drawRoundRect(
                brush = gradient,
                topLeft = Offset(x = 0f, y = y),
                size = Size(width = cellWidth, height = cellHeight),
                cornerRadius = radius,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PokemonStatsPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>,
) {
    HPokeTheme {
        PokemonStats(
            stats = pokemons.last().stats,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
