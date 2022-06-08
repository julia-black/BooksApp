package com.juliablack.booksapp.di

import com.juliablack.booksapp.ui.creating.CreateViewModel
import com.juliablack.booksapp.ui.details.DetailsViewModel
import com.juliablack.booksapp.ui.list.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val appModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { (url: String) -> DetailsViewModel(get(), url) }
    viewModel { CreateViewModel(get()) }
}