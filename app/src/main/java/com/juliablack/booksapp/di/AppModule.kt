package com.juliablack.booksapp.di

import com.juliablack.booksapp.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListViewModel(get()) }
}