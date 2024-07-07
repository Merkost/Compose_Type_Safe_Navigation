package com.merkost.composenavigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object HomeGraph

    @Serializable
    data object Home

    @Serializable
    data class Search(val searchText: String? = null)

    @Serializable
    data object Profile

    @Serializable
    data class ProfileInfo(val userId: String)
}

enum class BottomNavigation(val label: String, val icon: ImageVector, val route: Any) {
    HOME("Home", Icons.Filled.Home, Destinations.Home),
    SEARCH("Search", Icons.Filled.Search, Destinations.Search()),
    PROFILE("Profile", Icons.Filled.AccountCircle, Destinations.Profile);
}