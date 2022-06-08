package com.juliablack.domain.usecase

import com.juliablack.domain.BooksRepository
import com.juliablack.domain.model.BookDetailsRequestBody
import io.reactivex.Completable

class CreateBookUseCase(private val repository: BooksRepository) {
    fun invoke(title: String, author: String, price: String, image: String): Completable {
        return if (isInvalidFormats(title, author, price, image)) {
            Completable.error(Throwable("Invalid data format"))
        } else if (isInvalidDouble(price)) {
            Completable.error(Throwable("Invalid price format, use only numbers and dot"))
        } else {
            repository.createNewBook(BookDetailsRequestBody(title, author, image, price.toDouble()))
        }
    }

    private fun isInvalidFormats(title: String, author: String, price: String, image: String) =
        title.isBlank() || author.isBlank() || image.isBlank() || image.contains(" ")

    private fun isInvalidDouble(price: String): Boolean {
        return try {
            price.toDouble()
            false
        } catch (e: NumberFormatException) {
            true
        }
    }
}