package saini.ayush.rickandmorty.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import saini.ayush.*


class Repository {

    companion object {
        lateinit var repository: Repository
        private const val BASE_URL = "https://rickandmortyapi.com/graphql"

        fun getInstance(): Repository {
            if (!::repository.isInitialized) {
                repository = Repository()
            }
            return repository
        }

        private val apolloClient: ApolloClient = ApolloClient.builder()
            .serverUrl(BASE_URL)
            .build()

    }

    suspend fun getCharacters(page: Int) =
        apolloClient.query(GetCharactersQuery(Input.fromNullable(page))).await()

    suspend fun getCharacter(characterId: String) =
        apolloClient.query(GetCharacterQuery(characterId)).await()

    suspend fun getEpisodes(page: Int) =
        apolloClient.query(GetEpisodesQuery(Input.fromNullable(page))).await()

    suspend fun getEpisode(episodeId: String) =
        apolloClient.query(GetEpisodeQuery(episodeId)).await()

    suspend fun getLocations(page: Int) =
        apolloClient.query(GetLocationsQuery(Input.fromNullable(page))).await()

    suspend fun getLocation(locationId: String) =
        apolloClient.query(GetLocationQuery(locationId)).await()

}