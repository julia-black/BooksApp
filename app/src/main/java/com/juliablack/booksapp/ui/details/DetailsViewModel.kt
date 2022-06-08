package com.juliablack.booksapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliablack.domain.model.BookDetails
import com.juliablack.domain.usecase.GetBookDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(
    private val getBookDetailsUseCase: GetBookDetailsUseCase,
    url: String
) : ViewModel() {

    val liveBook = MutableLiveData<BookDetails>()
    val liveError = MutableLiveData<String>()

    var disposable: Disposable? = null

    init {
        loadDetails(url)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    private fun loadDetails(url: String) {
        disposable = getBookDetailsUseCase.invoke(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveBook.postValue(it)
            }, {
                liveError.postValue(it.message)
            })
    }
}