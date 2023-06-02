package com.mzhadan.phoneaccounting.repository.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.User
import com.mzhadan.phoneaccounting.remote.phones.PhonesApi
import retrofit2.Response
import javax.inject.Inject

class PhonesInfoRepositoryImpl @Inject constructor(
    private val phonesApi: PhonesApi
): PhonesInfoRepository {

    override suspend fun getAllPhonesInfo(): List<PhoneInfo> {
        return phonesApi.getAllPhonesInfo()
    }

    override suspend fun getPhoneById(phoneId: Int): PhoneInfo {
        return phonesApi.getPhoneById(phoneId)
    }

    override suspend fun getPhoneByManufacturer(mnfctr: String): PhoneInfo {
        return phonesApi.getPhoneByManufacturer(mnfctr)
    }

    override suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo): Response<PhoneInfo> {
        return phonesApi.addNewPhoneInfo(phoneInfo)
    }

    override suspend fun deletePhoneInfoById(phoneId: Int): Response<PhoneInfo> {
        return phonesApi.deletePhoneInfoById(phoneId)
    }

    override suspend fun updatePhoneInfoUser(phoneId: Int, user: User): Response<User> {
        return phonesApi.updatePhoneInfoUser(phoneId, user)
    }
}