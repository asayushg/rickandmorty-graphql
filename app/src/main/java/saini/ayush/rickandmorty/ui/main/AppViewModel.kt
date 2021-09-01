package saini.ayush.rickandmorty.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import saini.ayush.fragment.CharacterDetail
import saini.ayush.fragment.EpisodeDetail
import saini.ayush.fragment.LocationDetail
import saini.ayush.rickandmorty.repository.Repository
import saini.ayush.rickandmorty.ui.main.paging_sources.CharactersPagingSource
import saini.ayush.rickandmorty.ui.main.paging_sources.EpisodesPagingSource
import saini.ayush.rickandmorty.ui.main.paging_sources.LocationsPagingSource


class AppViewModel : ViewModel() {

    private val repository = Repository.getInstance()

    val characters: Flow<PagingData<CharacterDetail>> = Pager(PagingConfig(20)) {
        CharactersPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    val episodes: Flow<PagingData<EpisodeDetail>> = Pager(PagingConfig(20)) {
        EpisodesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    val locations: Flow<PagingData<LocationDetail>> = Pager(PagingConfig(20)) {
        LocationsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    suspend fun getCharacter(characterId: String): CharacterDetail? {
        val response = repository.getCharacter(characterId)
        return response.data?.character()?.fragments()?.characterDetail()
    }

    suspend fun getEpisode(episodeId: String): EpisodeDetail? {
        val response = repository.getEpisode(episodeId)
        return response.data?.episode()?.fragments()?.episodeDetail()
    }

    suspend fun getLocation(locationId: String): LocationDetail? {
        val response = repository.getLocation(locationId)
        return response.data?.location()?.fragments()?.locationDetail()
    }


}