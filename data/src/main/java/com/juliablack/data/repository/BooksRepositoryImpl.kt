package com.juliablack.data.repository

import com.juliablack.data.network.Api
import com.juliablack.domain.BooksRepository

class BooksRepositoryImpl(private val api: Api) : BooksRepository {

    override fun getBooks() = api.getBooks()

    override fun getBookDetails(url: String) = api.getBookDetails(url)
}