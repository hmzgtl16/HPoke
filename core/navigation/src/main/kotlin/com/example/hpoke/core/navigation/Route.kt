package com.example.hpoke.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
data object Home : NavKey

@Serializable
data class Details(val id: Int) : NavKey