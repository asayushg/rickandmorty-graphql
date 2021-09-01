package saini.ayush.rickandmorty.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import saini.ayush.rickandmorty.ui.Screen
import saini.ayush.rickandmorty.ui.detailsList.DetailsListViewModel

@Composable
fun RickMortyNavHost(
    navController: NavHostController,
    viewModel: DetailsListViewModel,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.CharactersList.name
    ) {
        composable(route = Screen.CharactersList.name) {
            CharactersListView(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = Screen.EpisodesList.name) {
            Text(text = Screen.EpisodesList.title)
        }
        composable(route = Screen.LocationsList.name) {
            Text(text = Screen.LocationsList.title)
        }
        val screenName = Screen.CharactersList.name
        composable(
            route = "$screenName/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val characterId = entry.arguments?.getString("id")
            characterId?.let {
                CharacterDetailView(viewModel = viewModel,
                    characterId = it,
                    popBack = { navController.popBackStack() })
            }
        }
    }

}