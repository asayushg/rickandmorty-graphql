package saini.ayush.rickandmorty.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import saini.ayush.rickandmorty.ui.Screen
import saini.ayush.rickandmorty.ui.components.character.CharacterDetailView
import saini.ayush.rickandmorty.ui.components.character.CharactersListView
import saini.ayush.rickandmorty.ui.components.episode.EpisodeDetailView
import saini.ayush.rickandmorty.ui.components.episode.EpisodesListView
import saini.ayush.rickandmorty.ui.components.location.LocationDetailView
import saini.ayush.rickandmorty.ui.components.location.LocationsListView
import saini.ayush.rickandmorty.ui.main.AppViewModel

@Composable
fun RickMortyNavHost(
    navController: NavHostController,
    viewModel: AppViewModel,
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
            EpisodesListView(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.LocationsList.name) {
            LocationsListView(viewModel = viewModel, navController = navController)
        }
        val screenName = Screen.CharactersList.name
        composable(
            route = "$screenName/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val characterId = entry.arguments?.getString("characterId")
            characterId?.let {
                CharacterDetailView(viewModel = viewModel,
                    characterId = it,
                    popBack = { navController.popBackStack() })
            }
        }


        val episodesScreenName = Screen.EpisodesList.name
        composable(
            route = "$episodesScreenName/{episodeId}",
            arguments = listOf(
                navArgument("episodeId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val episodeId = entry.arguments?.getString("episodeId")
            episodeId?.let {
                EpisodeDetailView(viewModel = viewModel,
                    episodeId = it,
                    popBack = { navController.popBackStack() })
            }
        }


        val locationScreenName = Screen.LocationsList.name
        composable(
            route = "$locationScreenName/{locationId}",
            arguments = listOf(
                navArgument("locationId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val episodeId = entry.arguments?.getString("locationId")
            episodeId?.let {
                LocationDetailView(
                    viewModel = viewModel,
                    locationId = it,
                    popBack = { navController.popBackStack() })
            }
        }


    }

}