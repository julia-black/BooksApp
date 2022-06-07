package com.juliablack.domain

class GetBooksUseCase(private val repository: BooksRepository) {
    fun invoke() = repository.getBooks()
}
