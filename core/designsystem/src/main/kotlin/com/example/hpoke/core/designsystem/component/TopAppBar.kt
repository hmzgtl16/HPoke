package com.example.hpoke.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HPokeTopAppBar(
    @StringRes titleRes: Int,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        colors = colors,
        modifier = modifier.testTag("hPokeTopAppBar"),
    )
}

@PreviewLightDark
@Composable
private fun HPokeTopAppBarPreview() {
    MaterialTheme {
        HPokeTopAppBar(
            titleRes = android.R.string.untitled
        )
    }
}