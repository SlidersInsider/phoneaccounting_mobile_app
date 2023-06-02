package com.mzhadan.phoneaccounting.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mzhadan.phoneaccounting.remote.notifications.NotificationsApi
import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import com.mzhadan.phoneaccounting.remote.sdcards.SdCardsApi
import com.mzhadan.phoneaccounting.remote.simcards.SimCardsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://phoneaccountingbackend-production.up.railway.app/api/v1/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providePhonesApi(retrofit: Retrofit) = retrofit.create(PhonesApi::class.java)

    @Provides
    @Singleton
    fun provideNotificationsApi(retrofit: Retrofit) = retrofit.create(NotificationsApi::class.java)

    @Provides
    @Singleton
    fun provideSimCardsApi(retrofit: Retrofit) = retrofit.create(SimCardsApi::class.java)

    @Provides
    @Singleton
    fun provideSdCardsApi(retrofit: Retrofit) = retrofit.create(SdCardsApi::class.java)

}