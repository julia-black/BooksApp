package com.juliablack.domain

import com.juliablack.domain.model.Book
import io.reactivex.rxjava3.core.Single

interface BooksRepository {
    fun getBooks(): Single<List<Book>>
}