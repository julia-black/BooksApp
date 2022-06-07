package com.juliablack.domain.model

data class BookDetailsRequestBody(
    val title: String,
    val author: String,
    val image: String,
    val price: Double
)