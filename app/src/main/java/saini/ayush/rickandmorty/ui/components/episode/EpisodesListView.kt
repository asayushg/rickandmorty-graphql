package saini.ayush.rickandmorty.ui.components.episode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import saini.ayush.rickandmorty.ui.Screen
import saini.ayush.rickandmorty.ui.main.AppViewModel

@Composable
fun EpisodesListView(
    viewModel: AppViewModel,
    navController: NavHostController,
) {
    val lazyEpisodesList = viewModel.episodes.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize()) {

        items(lazyEpisodesList) { episode ->
            episode?.let { it1 ->
                EpisodeView(
                    episode = it1,
                    episodeSelected = {
                        navController.navigate(Screen.EpisodesList.name + "/${it1.id()}")
                    }
                )
            }
        }
    }
}