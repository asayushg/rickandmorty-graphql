package saini.ayush.rickandmorty.ui.components.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import saini.ayush.fragment.LocationDetail

@Composable
fun LocationView(location: LocationDetail, locationSelected: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()
        .clickable(onClick = { locationSelected() })
        .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                location.name().orEmpty(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                maxLines = 1, overflow = TextOverflow.Ellipsis,)

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    "${location.residents().size} resident(s)",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
    Divider()
}