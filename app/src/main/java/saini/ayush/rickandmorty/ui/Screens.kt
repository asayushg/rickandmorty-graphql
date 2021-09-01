package saini.ayush.rickandmorty.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector


enum class Screen(
    val icon: ImageVector,
    val  placeholder:ImageVector,
    val title: String,
) {

    CharactersList(
        icon = Icons.Filled.Person,
        placeholder = Icons.Default.Person,
        title = "Characters"
    ),

    EpisodesList(
        icon = Icons.Filled.List,
        placeholder = Icons.Default.List,
        title = "Episodes"
    ),

    LocationsList(
        icon = Icons.Filled.Place,
        placeholder = Icons.Default.Place,
        title = "Locations"
    );

    companion object {

        private val listToShowBottomNav = listOf(CharactersList.name,EpisodesList.name,LocationsList.name)

        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                CharactersList.name -> CharactersList
                EpisodesList.name -> EpisodesList
                LocationsList.name -> LocationsList
                null -> CharactersList
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }

        fun showBottomNav(route: String):Boolean{
            return listToShowBottomNav.contains(route)
        }
    }

}