package saini.ayush.rickandmorty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import saini.ayush.fragment.CharacterDetail
import saini.ayush.rickandmorty.ui.detailsList.DetailsListViewModel

@Composable
fun CharacterDetailView(viewModel: DetailsListViewModel, characterId: String, popBack: () -> Unit) {
    val (character, setCharacter) = remember { mutableStateOf<CharacterDetail?>(null) }

    LaunchedEffect(characterId) {
        setCharacter(viewModel.getCharacter(characterId))
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Detail") },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White)
                    }
                }
            )
        })
    {

        Surface(color = Color.White) {

            LazyColumn {
                character?.let {

                    item(character.id()) {

                        Surface(
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                val imageUrl = character.image()
                                Surface(
                                    modifier = Modifier.size(150.dp),
                                    shape = RoundedCornerShape(25.dp),
                                ) {
                                    Image(painter = rememberImagePainter(imageUrl),
                                        contentDescription = character.name()
                                    )
                                }

                                Column {


                                    Text(
                                        it.name() + "",
                                        style = typography.h5,
                                        color = LocalContentColor.current,
                                        modifier = Modifier
                                            .padding(vertical = 8.dp, horizontal = 16.dp)
                                            .wrapContentSize(Alignment.Center)
                                    )

                                    Text(
                                        text = "Gender: " + it.gender(),
                                        style = typography.body1,
                                        color = LocalContentColor.current,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .wrapContentSize(Alignment.Center)
                                    )

                                    Text(
                                        text = "Origin: " + it.origin()?.name(),
                                        style = typography.body1,
                                        color = LocalContentColor.current,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .wrapContentSize(Alignment.Center)
                                    )

                                    Text(
                                        text = "Species: " + it.species(),
                                        style = typography.body1,
                                        color = LocalContentColor.current,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .padding(bottom = 8.dp)
                                            .wrapContentSize(Alignment.Center)
                                    )
                                }

                            }

                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Text(
                            "Episodes",
                            style = typography.h5,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )

                        Surface(color = Color.White) {
                            CharacterEpisodeList(character)
                        }

                    }

                }
            }

        }
    }
}

@Composable
private fun CharacterEpisodeList(character: CharacterDetail) {
    Column {
        character.episode().let { episodeList ->
            episodeList.filterNotNull().forEach { episode ->
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    episode.name()?.let {
                        Text(
                            it,
                            style = typography.h6)
                    }
                    episode.air_date()?.let {
                        Text(
                            it,
                            style = typography.subtitle2,
                            color = Color.Gray)
                    }
                }
                Divider()
            }
        }
    }
}