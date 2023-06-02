package com.mzhadan.phoneaccounting.repository.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.User
import retrofit2.Response

interface PhonesInfoRepository {
    suspend fun getAllPhonesInfo(): List<PhoneInfo>
    suspend fun getPhoneById(phoneId: Int): PhoneInfo
    suspend fun getPhoneByManufacturer(mnfctr: String): PhoneInfo
    suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo): Response<PhoneInfo>
    suspend fun deletePhoneInfoById(phoneId: Int): Response<PhoneInfo>
    suspend fun updatePhoneInfoUser(phoneId: Int, user: User): Response<User>
}