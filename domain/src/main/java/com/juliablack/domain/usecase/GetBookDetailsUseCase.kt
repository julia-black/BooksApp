package com.juliablack.domain.usecase

import com.juliablack.domain.BooksRepository

class GetBookDetailsUseCase(private val repository: BooksRepository) {
    fun invoke(url: String) = repository.getBookDetails(url)
}