package saini.ayush.rickandmorty.ui.components.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import saini.ayush.fragment.CharacterDetail

@Composable
fun CharactersListItemView(
    character: CharacterDetail,
    characterSelected: (character: CharacterDetail) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = { characterSelected(character) })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            modifier = Modifier.size(150.dp),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = rememberImagePainter(character.image()),
                contentDescription = character.name(),
                modifier = Modifier.size(150.dp),
            )
        }

        Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
            character.name()?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(modifier = Modifier.wrapContentSize(),verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color = when (character.status()) {
                            "Alive" -> Color.Green
                            "Dead" -> Color.Red
                            else -> Color.Gray
                        })
                        .wrapContentSize()
                )

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        "${character.status()} - ${character.species()}",
                        style = MaterialTheme.typography.body2
                    )
                }
            }



            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "First seen in:",
                style = MaterialTheme.typography.body1,
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    "${character.episode()[0].name()}",
                    style = MaterialTheme.typography.subtitle2
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Last known location:",
                style = MaterialTheme.typography.body1,
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    "${character.location()?.name()}",
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    "${character.episode().size} episode(s)",
                    style = MaterialTheme.typography.body2
                )
            }

        }

    }

    Divider()
}