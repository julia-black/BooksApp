package com.juliablack.domain.di

import com.juliablack.domain.usecase.CreateBookUseCase
import com.juliablack.domain.usecase.GetBookDetailsUseCase
import com.juliablack.domain.usecase.GetBooksUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetBooksUseCase(get()) }
    single { GetBookDetailsUseCase(get()) }
    single { CreateBookUseCase(get()) }
}