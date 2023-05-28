package com.mzhadan.phoneaccounting.repository.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo

interface PhonesInfoRepository {
    suspend fun getAllPhonesInfo(): List<PhoneInfo>
    suspend fun getPhoneById(phoneId: Int): List<PhoneInfo>
    suspend fun getPhoneByManufacturer(mnfctr: String): List<PhoneInfo>
    suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo)
    suspend fun deletePhoneInfoById(phoneId: Int)
    suspend fun updatePhoneInfoUser(phoneId: Int, name: String)
}