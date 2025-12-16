package com.example.hpoke.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.hpoke.core.designsystem.R
import com.example.hpoke.core.designsystem.theme.HPokeTheme

@Composable
fun HPokeImage(
    imageUrl: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.ic_placeholder_default),
    contentScale: ContentScale = ContentScale.Crop
) {

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HPokeCircularProgressIndicator(
                    modifier = Modifier.size(60.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        error = {
            Image(
                painter = placeholder,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = Modifier.fillMaxSize()
            )
        }
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
