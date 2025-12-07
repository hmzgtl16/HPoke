package com.example.hpoke.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Text(
        text = "Hello Home!",
        modifier = Modifier
    )
}