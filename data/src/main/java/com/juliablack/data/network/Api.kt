package com.juliablack.data.network

import com.juliablack.domain.model.Book
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface Api {
    @GET("/api/v1/items")
    fun getBooks(): Single<List<Book>>
}