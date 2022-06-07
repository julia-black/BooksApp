package com.juliablack.booksapp.ui.creating

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliablack.domain.usecase.CreateBookUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateViewModel(private val createBookUseCase: CreateBookUseCase) : ViewModel() {

    val liveComplete = MutableLiveData<Unit>()
    val liveError = MutableLiveData<String>()

    fun onClickCreate(title: String, author: String, price: String, imageLink: String) {
        createBookUseCase.invoke(title, author, price, imageLink)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveComplete.postValue(Unit)
            }, {
                liveError.postValue(it.message)
            })
    }
}