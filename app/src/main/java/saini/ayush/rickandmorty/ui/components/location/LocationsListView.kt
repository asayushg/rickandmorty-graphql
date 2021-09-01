package saini.ayush.rickandmorty.ui.components.location

import saini.ayush.rickandmorty.ui.components.episode.EpisodeView


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
fun LocationsListView(
    viewModel: AppViewModel,
    navController: NavHostController,
) {
    val lazyLocationsList = viewModel.locations.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize()) {

        items(lazyLocationsList) { location ->
            location?.let { it1 ->
                LocationView(
                    location = it1,
                    locationSelected = {
                        navController.navigate(Screen.LocationsList.name + "/${it1.id()}")
                    }
                )
            }
        }
    }
}


