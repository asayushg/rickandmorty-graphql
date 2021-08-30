package saini.ayush.rickandmorty.ui.detailsList

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import saini.ayush.GetCharactersQuery
import saini.ayush.rickandmorty.repository.Repository

class DetailsListViewModel : ViewModel() {

    private val repository = Repository.getInstance()
    var page = mutableStateOf(1)

    init {
        getCharacterDetails()
        Log.d("Characters", "init")
    }

    fun getCharacterDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                repository.getCharacters(page = 1)
            } catch (e: Exception) {
                Log.d("Characters", "getCharacterDetails: $e")
                return@launch
            }

            val characters: GetCharactersQuery.Data? = response.data

            if (response.hasErrors() || characters == null) {
                Log.d("Characters", "getCharacterDetails: ${response.errors}")
                return@launch
            }

            Log.d("Characters", "getCharacterDetails: $characters")

        }
    }

}