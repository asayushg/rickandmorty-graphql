package saini.ayush.rickandmorty.ui.components.episode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import saini.ayush.fragment.EpisodeDetail

@Composable
fun EpisodeView(
    episode: EpisodeDetail,
    episodeSelected: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = { episodeSelected() })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            episode.name()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = episode.air_date().orEmpty(),
                    style = MaterialTheme.typography.body2
                )
            }

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = episode.episode().orEmpty(),
                    style = MaterialTheme.typography.body2
                )
            }

        }

    }

    Divider()

}