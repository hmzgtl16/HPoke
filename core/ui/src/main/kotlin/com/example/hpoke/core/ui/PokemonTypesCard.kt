package com.example.hpoke.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.Top)
        ) {
            Text(
                text = stringResource(id = R.string.core_ui_types),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Top
                ),
                maxItemsInEachRow = 4
            ) {
                repeat(types.size) {
                    val type = remember(types[it].name) {
                        PokemonType.fromApi(types[it].name)
                    }

                    HPokeChip(
                        label = stringResource(id = type.labelRes),
                        colors = AssistChipDefaults.elevatedAssistChipColors(
                            containerColor = type.backgroundColor,
                            labelColor = type.textColor
                        )
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PokemonTypesCardPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {
    HPokeTheme {
        PokemonTypesCard(
            types = pokemons.first().types,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
