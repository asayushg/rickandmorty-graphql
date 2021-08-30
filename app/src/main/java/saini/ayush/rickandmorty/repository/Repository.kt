package saini.ayush.rickandmorty.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Repository {

    companion object {
        lateinit var repository: Repository
        const val BASE_URL = "https://rickandmortyapi.com/graphql"

        fun getInstance(): Repository {
            if (!::repository.isInitialized) {
                repository = Repository()
            }
            return repository
        }

    }

    fun getCharacterDetailsList() {
        CoroutineScope(Dispatchers.IO).launch {
            val apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .build()

       //    val response = try {
       //        apolloClient.query(
       //            CharacterDetailsList()
       //        ).toDeferred().await()
       //    } catch (e: ApolloException) {

       //    }
        }
    }

}