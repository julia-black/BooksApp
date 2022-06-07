package com.juliablack.booksapp

import android.app.Application
import com.juliablack.booksapp.di.appModule
import com.juliablack.data.di.dataModule
import com.juliablack.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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