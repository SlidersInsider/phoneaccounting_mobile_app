package com.mzhadan.phoneaccounting.repository.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo

interface PhoneAccountingRepository {
    suspend fun getAllPhonesInfo(): List<PhoneInfo>
    suspend fun getPhoneById(phoneId: Int): List<PhoneInfo>
    suspend fun getPhoneByManufacturer(mnfctr: String): List<PhoneInfo>
    suspend fun addNewPhoneInfo(phoneInfo: PhoneInfo)
}