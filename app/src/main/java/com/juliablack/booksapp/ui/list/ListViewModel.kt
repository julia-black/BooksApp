package com.juliablack.booksapp.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.juliablack.domain.model.Book
import com.juliablack.domain.usecase.GetBooksUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ListViewModel(
    private val getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    val liveBooks = MutableLiveData<PagingData<Book>>()
    val liveError = MutableLiveData<String>()

    private var offset = 0

    init {
        loadNextPage()
    }

//    private fun getBooks() {
//        getBooksUseCase.invoke(0)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                liveBooks.postValue(it)
//            }, {
//                liveError.postValue(it.message)
//            })
    //  }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadNextPage() {
        getBooksUseCase.invoke(offset, COUNT).cachedIn(viewModelScope)
            .subscribe({
                liveBooks.postValue(it)
            }, {
                liveError.postValue(it.message)
            })
            .dispose()
    }

    fun onScrolled(lastVisiblePos: Int) {
        if (lastVisiblePos >= COUNT - 1 + offset) {
            offset += COUNT
            Log.d("123LOG", "$lastVisiblePos $offset")
            loadNextPage()
        }
    }

    companion object {
        private const val COUNT = 10
    }
}