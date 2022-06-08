package com.juliablack.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.observable
import com.juliablack.data.network.Api
import com.juliablack.data.network.BooksPagingSource
import com.juliablack.domain.BooksRepository
import com.juliablack.domain.model.BookDetailsRequestBody

@ExperimentalPagingApi
class BooksRepositoryImpl(private val api: Api) : BooksRepository {

    override fun getBooks(count: Int) = Pager(
        config = PagingConfig(count),
        pagingSourceFactory = { BooksPagingSource(api) }
    ).observable

    override fun getBookDetails(url: String) = api.getBookDetails(url)

    override fun createNewBook(newBook: BookDetailsRequestBody) = api.createNewBook(newBook)
}