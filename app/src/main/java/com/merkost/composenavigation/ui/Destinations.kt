package com.merkost.composenavigation.ui

sealed class Destinations(val route: String) {
    data object HOME : Destinations("home")
    data object PROFILE : Destinations("profile?userId={userId}")
}

fun Destinations.PROFILE.buildRoute(userId: String): String = "profile?userId=$userId"