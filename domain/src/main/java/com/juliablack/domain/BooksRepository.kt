package com.juliablack.domain

import androidx.paging.PagingData
import com.juliablack.domain.model.Book
import com.juliablack.domain.model.BookDetails
import com.juliablack.domain.model.BookDetailsRequestBody
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface BooksRepository {
    fun getBooks(count: Int): Observable<PagingData<Book>>

    fun getBookDetails(url: String): Single<BookDetails>

    fun createNewBook(newBook: BookDetailsRequestBody): Completable
}