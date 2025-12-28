package com.example.hpoke.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.hpoke.core.designsystem.component.HPokeImage
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.ui.palette.paletteBackgroundColor
import com.example.hpoke.core.ui.palette.paletteTextColor
import com.example.hpoke.core.ui.preview.PokemonPreviewParameterProvider
import com.skydoves.landscapist.palette.rememberPaletteState

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var palette by rememberPaletteState()
    val backgroundColor by palette.paletteBackgroundColor()
    val textColor by palette.paletteTextColor()

    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(10),
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onPokemonClick(pokemon.id) }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HPokeImage(
                imageUrl = pokemon.species.frontDefault,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp)
                    .align(alignment = Alignment.Center),
                onPaletteLoaded = { palette = it },
                contentScale = ContentScale.Inside
            )

            Text(
                text = pokemon.name.replaceFirstChar(Char::titlecase),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PokemonCardPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) pokemons: List<Pokemon>
) {
    HPokeTheme {
        PokemonCard(
            pokemon = pokemons.first(),
            onPokemonClick = {},
            modifier = Modifier.size(size = 200.dp)
        )
    }
}
