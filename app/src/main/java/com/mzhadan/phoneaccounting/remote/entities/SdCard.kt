package com.mzhadan.phoneaccounting.remote.entities

import com.google.gson.annotations.SerializedName

data class SdCard(
    @SerializedName("id")
    var sdcardId: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("serial_number")
    var serialNumber: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("in_phone")
    var inPhone: Boolean
)
