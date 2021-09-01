package saini.ayush.rickandmorty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.NavHostController
import saini.ayush.rickandmorty.ui.Screen

@Composable
fun RickMortyBottomNav(
    allScreens: List<Screen>,
    currentScreen: Screen,
    navController: NavHostController,
) {

    BottomNavigation(modifier = Modifier.fillMaxWidth()) {
        allScreens.forEach {
            BottomNavigationItem(
                selected = currentScreen == it,
                onClick = { if (currentScreen != it) navController.navigate(it.name) },
                icon = {
                    Image(
                        imageVector = if (currentScreen == it) it.icon else it.placeholder,
                        contentDescription = it.title,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                },
                label = { Text(text = it.title) }
            )
        }
    }

}