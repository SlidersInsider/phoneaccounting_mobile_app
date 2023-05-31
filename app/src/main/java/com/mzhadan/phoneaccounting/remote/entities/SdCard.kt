package com.mzhadan.phoneaccounting.remote.entities

data class SdCard(
    var sdcardId: Int,
    var name: String,
    var serialNumber: String,
    var size: String,
    var inPhone: String
)
