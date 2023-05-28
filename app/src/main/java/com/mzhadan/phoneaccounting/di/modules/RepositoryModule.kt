package com.mzhadan.phoneaccounting.di.modules

import com.mzhadan.phoneaccounting.remote.notifications.NotificationsApi
import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepository
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepositoryImpl
import com.mzhadan.phoneaccounting.repository.phones.PhonesInfoRepository
import com.mzhadan.phoneaccounting.repository.phones.PhonesInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePhonesRepository(phonesApi: PhonesApi): PhonesInfoRepository =
        PhonesInfoRepositoryImpl(phonesApi)

    @Provides
    @Singleton
    fun provideNotificationsRepository(notificationsApi: NotificationsApi): NotificationsRepository =
        NotificationsRepositoryImpl(notificationsApi)
}