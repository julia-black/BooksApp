package com.juliablack.booksapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliablack.domain.model.BookDetails
import com.juliablack.domain.usecase.GetBookDetailsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(
    private val getBookDetailsUseCase: GetBookDetailsUseCase,
    url: String
) : ViewModel() {

    val liveBook = MutableLiveData<BookDetails>()
    val liveError = MutableLiveData<String>()

    init {
        loadDetails(url)
    }

    private fun loadDetails(url: String) {
        getBookDetailsUseCase.invoke(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveBook.postValue(it)
            }, {
                liveError.postValue(it.message)
            })
    }
}