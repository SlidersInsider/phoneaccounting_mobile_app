package com.mzhadan.phoneaccounting.repository.phones


import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import javax.inject.Inject

class PhoneAccountingRepositoryImpl @Inject constructor(
    private val phonesApi: PhonesApi
): PhoneAccountingRepository {

    override suspend fun getAllPhonesInfo(): List<PhoneInfo> {
        return phonesApi.getAllPhonesInfo()
    }

    override suspend fun getPhoneById(phoneId: Int): List<PhoneInfo> {
        return phonesApi.getPhoneById(phoneId)
    }

    override suspend fun getPhoneByManufacturer(mnfctr: String): List<PhoneInfo> {
        return phonesApi.getPhoneByManufacturer(mnfctr)
    }

    override suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo) {
        return phonesApi.addNewPhoneInfo(phoneInfo)
    }
}