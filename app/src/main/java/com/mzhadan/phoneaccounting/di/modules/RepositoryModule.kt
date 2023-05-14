package com.mzhadan.phoneaccounting.di.modules

import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import com.mzhadan.phoneaccounting.repository.phones.PhoneAccountingRepository
import com.mzhadan.phoneaccounting.repository.phones.PhoneAccountingRepositoryImpl
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
    fun providePhonesRepository(phonesApi: PhonesApi): PhoneAccountingRepository =
        PhoneAccountingRepositoryImpl(phonesApi)
}