package com.juliablack.data.network

import com.juliablack.domain.model.Book
import com.juliablack.domain.model.BookDetails
import com.juliablack.domain.model.BookDetailsRequestBody
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    @GET("/api/v1/items")
    suspend fun getBooks(@Query("offset") offset: Int, @Query("count") count: Int): List<Book>

    @GET
    fun getBookDetails(@Url url: String): Single<BookDetails>

    @POST("/api/v1/items")
    fun createNewBook(@Body bode: BookDetailsRequestBody): Completable
}