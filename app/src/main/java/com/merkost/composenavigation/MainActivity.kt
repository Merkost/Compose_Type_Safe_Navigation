package com.merkost.composenavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.serialization.generateRouteWithArgs
import androidx.navigation.toRoute
import com.merkost.composenavigation.ui.BottomNavigation
import com.merkost.composenavigation.ui.Destinations
import com.merkost.composenavigation.ui.screens.HomeScreen
import com.merkost.composenavigation.ui.screens.ProfileInfoScreen
import com.merkost.composenavigation.ui.theme.ComposeTypeSafeNavigationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTypeSafeNavigationTheme {

                val navController = rememberNavController()

val navBackStackEntry by navController.currentBackStackEntryAsState()
val currentRoute = navBackStackEntry?.destination?.route
    ?: BottomNavigation.HOME::class.qualifiedName.orEmpty()

                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            BottomNavigation.entries
                                .forEachIndexed { index, navigationItem ->

                                    val isSelected by remember(currentRoute) {
                                        derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                                    }

                                    NavigationBarItem(
                                        selected = isSelected,
                                        label = { Text(navigationItem.label) },
                                        icon = {
                                            Icon(
                                                navigationItem.icon,
                                                contentDescription = navigationItem.label
                                            )
                                        },
                                        onClick = {
                                            navController.navigate(navigationItem.route)
                                        }
                                    )
                                }
                        }
                    }
                ) {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController,
                        startDestination = Destinations.HomeGraph
                    ) {
                        navigation<Destinations.HomeGraph>(
                            startDestination = Destinations.Home,
                        ) {

                            composable<Destinations.Home> {
                                HomeScreen(
                                    toProfileScreen = {
                                        navController.navigate(Destinations.ProfileInfo("101"))
                                    }
                                )
                            }

                            composable<Destinations.Search> {
                                Greeting("Search")
                            }

                            composable<Destinations.Profile> { backStackEntry ->
                                Greeting("Profile")
                            }
                        }

                        composable<Destinations.ProfileInfo> { backStackEntry ->
                            val profileInfo = backStackEntry.toRoute<Destinations.ProfileInfo>()
                            ProfileInfoScreen(userId = profileInfo.userId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTypeSafeNavigationTheme {
        Greeting("Android")
    }
}