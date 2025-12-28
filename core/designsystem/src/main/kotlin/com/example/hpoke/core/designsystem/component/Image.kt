package com.example.hpoke.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.hpoke.core.designsystem.R
import com.example.hpoke.core.designsystem.theme.HPokeTheme
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun HPokeImage(
    imageUrl: String?,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.ic_placeholder_default),
    previewPlaceholder: Painter = painterResource(id = R.drawable.preview_placeholder),
    contentScale: ContentScale = ContentScale.Crop,
    onPaletteLoaded: (Palette) -> Unit = {}
) {
    val component = rememberImageComponent {
        +ShimmerPlugin(
            shimmer = Shimmer.Flash(
                baseColor = Color.White,
                highlightColor = Color.LightGray,
            )
        )
        +PlaceholderPlugin.Failure(source = placeholder)
        +PalettePlugin(
            imageModel = imageUrl,
            useCache = true,
            paletteLoadedListener = onPaletteLoaded::invoke
        )
    }

    CoilImage(
        modifier = modifier,
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            contentDescription = contentDescription,
            alignment = Alignment.BottomCenter
        ),
        component = component,
        previewPlaceholder = previewPlaceholder,
    )
}

@PreviewLightDark
@Composable
fun HPokeImagePreview() {
    HPokeTheme {
        HPokeImage(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/13",
            contentDescription = "Pokemon ditto",
            modifier = Modifier
                .size(size = 120.dp)
                .clip(shape = RoundedCornerShape(size = 16.dp))
        )
    }
}
