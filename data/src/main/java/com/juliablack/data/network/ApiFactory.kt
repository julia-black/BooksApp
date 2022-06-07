package com.juliablack.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun createApi(client: OkHttpClient, gson: Gson): Api = Retrofit
    .Builder()
    .client(client)
    .baseUrl("https://www.google.com/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()
    .create(Api::class.java)

fun createGson(): Gson = GsonBuilder()
    .setLenient()
    .create()

fun createOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(interceptor)
    .build()