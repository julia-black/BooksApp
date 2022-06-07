package com.juliablack.data.network

import com.juliablack.domain.model.Book
import com.juliablack.domain.model.BookDetails
import com.juliablack.domain.model.BookDetailsRequestBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface Api {
    @GET("/api/v1/items")
    fun getBooks(): Single<List<Book>>

    @GET
    fun getBookDetails(@Url url: String): Single<BookDetails>

    @POST("/api/v1/items")
    fun createNewBook(@Body bode: BookDetailsRequestBody): Completable
}