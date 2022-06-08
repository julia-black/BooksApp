package com.juliablack.data.di

import androidx.paging.ExperimentalPagingApi
import com.juliablack.data.network.MockInterceptor
import com.juliablack.data.network.createApi
import com.juliablack.data.network.createGson
import com.juliablack.data.network.createOkHttpClient
import com.juliablack.data.repository.BooksRepositoryImpl
import com.juliablack.domain.BooksRepository
import okhttp3.Interceptor
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val dataModule = module {
    factory<Interceptor> { MockInterceptor() }
    single { createGson() }
    single { createOkHttpClient(get()) }
    factory<BooksRepository> { BooksRepositoryImpl(createApi(get(), get())) }
}