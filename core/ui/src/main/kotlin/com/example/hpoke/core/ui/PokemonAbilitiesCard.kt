package com.example.hpoke.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Ability
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider

@Composable
fun PokemonAbilitiesCard(
    abilities: List<Ability>,
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
                text = stringResource(id = R.string.core_ui_abilities),
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
                )
            ) {
                repeat(abilities.size) {
                    val ability = remember(
                        key1 = abilities[it].name,
                        calculation = abilities[it]::name
                    )

                    Text(
                        text = ability.replaceFirstChar(Char::titlecase),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PokemonAbilitiesCardPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {
    HPokeTheme {
        PokemonAbilitiesCard(
            abilities = pokemons.first().abilities,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
