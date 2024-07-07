package com.merkost.composenavigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(val route: String) {

    data object HomeGraph: Destinations("home_graph")
    data object Home: Destinations("home")
    data object Search: Destinations("search")
    data object Profile: Destinations("profile")
    data object ProfileInfo: Destinations("profile_info?userId={userId}")
}

fun Destinations.ProfileInfo.buildRoute(userId: String): String {
    return "profile_info?userId=$userId"
}

enum class BottomNavigation(val label: String, val icon: ImageVector, val route: String) {
    HOME("Home", Icons.Filled.Home, Destinations.Home.route),
    SEARCH("Search", Icons.Filled.Search, Destinations.Search.route),
    PROFILE("Profile", Icons.Filled.AccountCircle, Destinations.Profile.route);
}