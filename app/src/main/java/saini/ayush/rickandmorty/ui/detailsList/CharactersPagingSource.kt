package saini.ayush.rickandmorty.ui.detailsList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import saini.ayush.fragment.CharacterDetail
import saini.ayush.rickandmorty.repository.Repository
import java.io.IOException

class CharactersPagingSource(
    private val repository: Repository,
) : PagingSource<Int, CharacterDetail>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetail> {
        val pageNumber = params.key ?: 0
        return try {
            val charactersResponse = repository.getCharacters(pageNumber)
            val characters = charactersResponse.data?.characters()?.results()
                ?.mapNotNull { it?.fragments()?.characterDetail() } ?: listOf()
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = charactersResponse.data?.characters()?.info()?.next()
            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDetail>): Int? {
        return null
    }

}