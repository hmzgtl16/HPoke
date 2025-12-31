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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.hpoke.core.designsystem.component.HPokeChip
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.model.Type
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider

@Composable
fun PokemonTypesCard(
    types: List<Type>,
    modifier: Modifier = Modifier,
) {
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
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.Top,
            ),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.core_ui_types),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                verticalArrangement =
                    Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.Top,
                    ),
                maxItemsInEachRow = 4,
            ) {
                repeat(types.size) {
                    val type =
                        remember(types[it].name) {
                            PokemonType.fromApi(types[it].name)
                        }

                    HPokeChip(
                        label = stringResource(id = type.labelRes),
                        colors =
                            AssistChipDefaults.elevatedAssistChipColors(
                                containerColor = type.backgroundColor,
                                labelColor = type.textColor,
                            ),
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PokemonTypesCardPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>,
) {
    HPokeTheme {
        PokemonTypesCard(
            types = pokemons.first().types,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
