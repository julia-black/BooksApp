package com.juliablack.booksapp

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.juliablack.booksapp.di.appModule
import com.juliablack.data.di.dataModule
import com.juliablack.domain.di.domainModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                dataModule,
                domainModule
            )
        }
    }
}