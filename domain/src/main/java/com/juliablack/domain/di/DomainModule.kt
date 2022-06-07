package com.juliablack.domain.di

import com.juliablack.domain.GetBooksUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetBooksUseCase(get()) }
}