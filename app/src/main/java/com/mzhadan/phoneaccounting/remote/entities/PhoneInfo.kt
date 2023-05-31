package com.mzhadan.phoneaccounting.remote.entities

import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo

data class PhoneInfo(
    val phoneId: Int,
    val model: String,
    val manufacturer: String,
    val osVersion: String,
    var convertedOsVersion: String,
    val firmware: String,
    val supportedArch: String,
    val user: String,
    val simSlotsCount: Int,
    var simcard1: String,
    var simcard2: String,
    val sdSlotsCount: Int,
    var sdcardSerialNumber: String
) {
    companion object {
        fun mapToLocalPhoneInfo(phoneInfoList: List<PhoneInfo>): List<LocalPhoneInfo> =
            phoneInfoList.map {
                LocalPhoneInfo(it.phoneId, it.model, it.manufacturer, it.osVersion,
                    it.convertedOsVersion, it.firmware, it.supportedArch, it.user,
                    it.simSlotsCount, it.simcard1, it.simcard2, it.sdSlotsCount,
                    it.sdcardSerialNumber)
            }
    }
}
