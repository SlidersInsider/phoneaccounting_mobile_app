package com.mzhadan.phoneaccounting.remote.entities

import com.google.gson.annotations.SerializedName
import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo

data class PhoneInfo(
    @SerializedName("id")
    val phoneId: Int,
    @SerializedName("model")
    val model: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("os_version")
    val osVersion: String,
    @SerializedName("converted_os_version")
    var convertedOsVersion: String,
    @SerializedName("firmware")
    val firmware: String,
    @SerializedName("supported_arch")
    val supportedArch: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("sim_slots_count")
    val simSlotsCount: Int,
    @SerializedName("simcard1")
    var simcard1: String,
    @SerializedName("simcard2")
    var simcard2: String,
    @SerializedName("sd_slots_count")
    val sdSlotsCount: Int,
    @SerializedName("sdcard_serial_number")
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
