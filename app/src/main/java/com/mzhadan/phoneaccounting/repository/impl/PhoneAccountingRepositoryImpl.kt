package com.mzhadan.phoneaccounting.repository.impl


import com.mzhadan.phoneaccounting.api.PhoneAccountingApiService
import com.mzhadan.phoneaccounting.models.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.PhoneAccountingRepository

class PhoneAccountingRepositoryImpl: PhoneAccountingRepository {

    override suspend fun getAllPhonesInfo(): List<PhoneInfo> {
        return PhoneAccountingApiService.phoneAccountingApi.getAllPhonesInfo()
    }

    override suspend fun getPhoneById(phoneId: Int): List<PhoneInfo> {
        return PhoneAccountingApiService.phoneAccountingApi.getPhoneById(phoneId)
    }

    override suspend fun getPhoneByManufacturer(mnfctr: String): List<PhoneInfo> {
        return PhoneAccountingApiService.phoneAccountingApi.getPhoneByManufacturer(mnfctr)
    }

    override suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo) {
        return PhoneAccountingApiService.phoneAccountingApi.addNewPhoneInfo(phoneInfo)
    }
}