package saini.ayush.rickandmorty.ui.components.character

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import saini.ayush.rickandmorty.ui.Screen
import saini.ayush.rickandmorty.ui.main.AppViewModel


@Composable
fun CharactersListView(
    viewModel: AppViewModel,
    navController: NavHostController,
) {

    val lazyCharactersList = viewModel.characters.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        state = listState
    ) {

        items(lazyCharactersList) { character ->
            character?.let { it1 ->
                CharactersListItemView(
                    character = it1,
                    characterSelected = { characterDetail ->
                        navController.navigate(Screen.CharactersList.name + "/${characterDetail.id()}")
                    }
                )
            }
        }
    }

}