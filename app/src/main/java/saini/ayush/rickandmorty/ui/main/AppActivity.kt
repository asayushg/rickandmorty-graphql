package saini.ayush.rickandmorty.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import saini.ayush.rickandmorty.ui.Screen
import saini.ayush.rickandmorty.ui.components.RickMortyBottomNav
import saini.ayush.rickandmorty.ui.components.RickMortyNavHost
import saini.ayush.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(viewModel)
        }
    }
}


@Composable
fun App(viewModel: AppViewModel) {

    RickAndMortyTheme {
        val allScreens = Screen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = Screen.fromRoute(
            backStackEntry.value?.destination?.route
        )

        val showBar =
            Screen.showBottomNav(navController.currentDestination?.route.toString())

        Scaffold(

            bottomBar = {
                if (showBar)
                    RickMortyBottomNav(
                        allScreens = allScreens,
                        currentScreen = currentScreen,
                        navController = navController
                    )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                RickMortyNavHost(navController, viewModel)
            }
        }

    }

}