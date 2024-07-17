package com.merkost.composenavigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object HomeGraph: Destinations()

    @Serializable
    data object Home: Destinations()

    @Serializable
    data class Search(val searchText: String? = null): Destinations()

    @Serializable
    data object Profile: Destinations()

    @Serializable
    data class ProfileInfo(val userId: String): Destinations()
}

enum class BottomNavigation(val label: String, val icon: ImageVector, val route: Destinations) {
    HOME("Home", Icons.Filled.Home, Destinations.Home),
    SEARCH("Search", Icons.Filled.Search, Destinations.Search()),
    PROFILE("Profile", Icons.Filled.AccountCircle, Destinations.Profile);
}