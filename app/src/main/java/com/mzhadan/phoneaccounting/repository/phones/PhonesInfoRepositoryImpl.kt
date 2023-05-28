package com.mzhadan.phoneaccounting.repository.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import javax.inject.Inject

class PhonesInfoRepositoryImpl @Inject constructor(
    private val phonesApi: PhonesApi
): PhonesInfoRepository {

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

    override suspend fun deletePhoneInfoById(phoneId: Int) {
        return phonesApi.deletePhoneInfoById(phoneId)
    }

    override suspend fun updatePhoneInfoUser(phoneId: Int, name: String) {
        return phonesApi.updatePhoneInfoUser(phoneId, name)
    }
}