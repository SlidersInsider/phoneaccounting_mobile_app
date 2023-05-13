package com.mzhadan.phoneaccounting.models.entities

data class PhoneInfo(
    val phoneId: Int,
    val model: String,
    val manufacturer: String,
    val osVersion: String,
    val firmware: String,
    val supportedArch: String,
    val user: String,
    val simSlotsCount: Int,
    var simcard1: String,
    var simcard2: String,
    val sdSlotsCount: Int,
    val sdcard: String
)
