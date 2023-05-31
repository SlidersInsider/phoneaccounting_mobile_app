package com.mzhadan.phoneaccounting.di.modules

import com.mzhadan.phoneaccounting.remote.notifications.NotificationsApi
import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import com.mzhadan.phoneaccounting.remote.sdcards.SdCardsApi
import com.mzhadan.phoneaccounting.remote.simcards.SimCardsApi
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepository
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepositoryImpl
import com.mzhadan.phoneaccounting.repository.phones.PhonesInfoRepository
import com.mzhadan.phoneaccounting.repository.phones.PhonesInfoRepositoryImpl
import com.mzhadan.phoneaccounting.repository.sdcards.SdCardsRepository
import com.mzhadan.phoneaccounting.repository.sdcards.SdCardsRepositoryImpl
import com.mzhadan.phoneaccounting.repository.simcards.SimCardsRepository
import com.mzhadan.phoneaccounting.repository.simcards.SimCardsRepositoryImpl
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

    @Provides
    @Singleton
    fun provideSimCardsRepository(simCardsApi: SimCardsApi): SimCardsRepository =
        SimCardsRepositoryImpl(simCardsApi)

    @Provides
    @Singleton
    fun provideSdCardsRepository(sdCardsApi: SdCardsApi): SdCardsRepository =
        SdCardsRepositoryImpl(sdCardsApi)
}