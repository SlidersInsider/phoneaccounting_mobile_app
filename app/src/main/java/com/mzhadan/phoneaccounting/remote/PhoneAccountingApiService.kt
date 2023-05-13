package com.mzhadan.phoneaccounting.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mzhadan.phoneaccounting.remote.phones.PhoneAccountingApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhoneAccountingApiService {
    private val gson: Gson = GsonBuilder().create()
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.109:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
    val phonesApi: PhoneAccountingApi by lazy {
        retrofit.create(PhoneAccountingApi::class.java)
    }
}