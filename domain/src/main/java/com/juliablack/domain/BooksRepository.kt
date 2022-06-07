package com.juliablack.domain

import com.juliablack.domain.model.Book
import com.juliablack.domain.model.BookDetails
import com.juliablack.domain.model.BookDetailsRequestBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface BooksRepository {
    fun getBooks(): Single<List<Book>>

    fun getBookDetails(url: String): Single<BookDetails>

    fun createNewBook(newBook: BookDetailsRequestBody): Completable
}