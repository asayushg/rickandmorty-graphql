package saini.ayush.rickandmorty.ui.main.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import saini.ayush.fragment.LocationDetail
import saini.ayush.rickandmorty.repository.Repository
import java.io.IOException

class LocationsPagingSource(
    private val repository: Repository
) :PagingSource<Int,LocationDetail>(){
    override fun getRefreshKey(state: PagingState<Int, LocationDetail>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDetail> {
        val pageNumber = params.key ?: 0
        return try {
            val locationsResponse = repository.getLocations(pageNumber)
            val locations = locationsResponse.data?.locations()?.results()
                ?.mapNotNull { it?.fragments()?.locationDetail() } ?: listOf()
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = locationsResponse.data?.locations()?.info()?.next()
            LoadResult.Page(
                data = locations,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}