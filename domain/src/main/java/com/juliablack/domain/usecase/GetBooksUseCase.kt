package com.juliablack.domain.usecase

import com.juliablack.domain.BooksRepository

class GetBooksUseCase(private val repository: BooksRepository) {
    fun invoke() = repository.getBooks()
}
