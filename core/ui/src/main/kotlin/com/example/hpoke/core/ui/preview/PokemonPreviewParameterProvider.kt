package com.example.hpoke.core.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.hpoke.core.model.Pokemon

class PokemonPreviewParameterProvider : PreviewParameterProvider<List<Pokemon>> {

    override val values: Sequence<List<Pokemon>> =
        sequenceOf(PreviewParameterData.pokemons)
}