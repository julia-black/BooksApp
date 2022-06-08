package com.juliablack.booksapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.juliablack.domain.model.Book
import com.juliablack.domain.usecase.GetBooksUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ListViewModel(
    private val getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    val liveBooks = MutableLiveData<PagingData<Book>>()
    val liveError = MutableLiveData<String>()

    init {
        loadBooks()
    }

    private fun loadBooks() {
        getBooksUseCase.invoke(COUNT).cachedIn(viewModelScope)
            .subscribe({
                liveBooks.postValue(it)
            }, {
                liveError.postValue(it.message)
            })
            .dispose()
    }

    companion object {
        private const val COUNT = 10
    }
}