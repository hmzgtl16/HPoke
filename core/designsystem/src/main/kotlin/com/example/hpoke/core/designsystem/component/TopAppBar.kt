package com.example.hpoke.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.hpoke.core.designsystem.icon.HPokeIcons
import com.example.hpoke.core.designsystem.theme.HPokeTheme

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HPokeTopAppBar(
    title: String,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
) {

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier.testTag("hPokeTopAppBar"),
        navigationIcon = {
            IconButton(
                onClick = onNavigationClick,
                content = {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription
                    )
                }
            )
        },
        colors = colors,
    )
}

@PreviewLightDark
@Composable
private fun HPokeTopAppBarPreview() {

    HPokeTheme {
        HPokeTopAppBar(
            titleRes = android.R.string.untitled
        )
    }
}

@PreviewLightDark
@Composable
private fun HPokeTopAppBarWithNavigationIconPreview() {

    HPokeTheme {
        HPokeTopAppBar(
            title = "Pokemon",
            navigationIcon = HPokeIcons.Back,
            navigationIconContentDescription = "Back"
        )
    }
}