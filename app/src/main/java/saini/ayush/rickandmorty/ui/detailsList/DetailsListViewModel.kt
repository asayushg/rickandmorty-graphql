package saini.ayush.rickandmorty.ui.detailsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import saini.ayush.fragment.CharacterDetail
import saini.ayush.rickandmorty.repository.Repository


class DetailsListViewModel : ViewModel() {

    private val repository = Repository.getInstance()

    val characters: Flow<PagingData<CharacterDetail>> = Pager(PagingConfig(20)) {
        CharactersPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    suspend fun getCharacter(characterId: String): CharacterDetail? {
        val response =  repository.getCharacter(characterId)
        return response.data?.character()?.fragments()?.characterDetail()
    }


}