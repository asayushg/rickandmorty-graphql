package saini.ayush.rickandmorty.ui.main.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import saini.ayush.fragment.EpisodeDetail
import saini.ayush.rickandmorty.repository.Repository
import java.io.IOException

class EpisodesPagingSource(
    private val repository: Repository,
) : PagingSource<Int, EpisodeDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeDetail> {
        val pageNumber = params.key ?: 0
        return try {
            val episodesResponse = repository.getEpisodes(pageNumber)
            val episodes = episodesResponse.data?.episodes()?.results()
                ?.mapNotNull { it?.fragments()?.episodeDetail() } ?: listOf()
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = episodesResponse.data?.episodes()?.info()?.next()
            LoadResult.Page(
                data = episodes,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeDetail>): Int? {
        return null
    }
}