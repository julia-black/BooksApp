package com.juliablack.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juliablack.domain.model.Book
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class BooksPagingSource(private val api: Api) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = api.getBooks(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }

    companion object {
        const val DEFAULT_PAGE_INDEX = 0
    }
}