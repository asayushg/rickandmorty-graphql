package saini.ayush.rickandmorty.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import saini.ayush.GetCharactersQuery


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


}