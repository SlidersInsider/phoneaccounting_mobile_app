package com.mzhadan.phoneaccounting.repository.impl


import com.mzhadan.phoneaccounting.remote.PhoneAccountingApiService
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.PhoneAccountingRepository

class PhoneAccountingRepositoryImpl: PhoneAccountingRepository {

    override suspend fun getAllPhonesInfo(): List<PhoneInfo> {
        return PhoneAccountingApiService.phonesApi.getAllPhonesInfo()
    }

    override suspend fun getPhoneById(phoneId: Int): List<PhoneInfo> {
        return PhoneAccountingApiService.phonesApi.getPhoneById(phoneId)
    }

    override suspend fun getPhoneByManufacturer(mnfctr: String): List<PhoneInfo> {
        return PhoneAccountingApiService.phonesApi.getPhoneByManufacturer(mnfctr)
    }

    override suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo) {
        return PhoneAccountingApiService.phonesApi.addNewPhoneInfo(phoneInfo)
    }
}