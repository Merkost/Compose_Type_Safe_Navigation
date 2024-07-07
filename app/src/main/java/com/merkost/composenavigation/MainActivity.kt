package com.merkost.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.merkost.composenavigation.ui.Destinations
import com.merkost.composenavigation.ui.buildRoute
import com.merkost.composenavigation.ui.screens.HomeScreen
import com.merkost.composenavigation.ui.screens.ProfileScreen
import com.merkost.composenavigation.ui.theme.ComposeTypeSafeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTypeSafeNavigationTheme {

                val navController = rememberNavController()

                Scaffold {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController,
                        startDestination = Destinations.HOME.route
                    ) {
                        composable(route = Destinations.HOME.route) {
                            HomeScreen(
                                toProfileScreen = {
                                    navController.navigate(Destinations.PROFILE.buildRoute("101"))
                                }
                            )
                        }
                        composable(
                            route = Destinations.PROFILE.route,
                            arguments = listOf(navArgument("userId") { defaultValue = "" })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId")
                            ProfileScreen(userId = userId)
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