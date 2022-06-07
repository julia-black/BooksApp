package com.juliablack.booksapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliablack.domain.usecase.GetBooksUseCase
import com.juliablack.domain.model.Book
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel(
    private val getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    val liveBooks = MutableLiveData<List<Book>>()
    val liveError = MutableLiveData<String>()

    init {
        getBooks()
    }

    private fun getBooks() {
        getBooksUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveBooks.postValue(it)
            }, {
                liveError.postValue(it.message)
            })
    }
}