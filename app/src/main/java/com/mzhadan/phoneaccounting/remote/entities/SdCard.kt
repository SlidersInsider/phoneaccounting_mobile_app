package com.mzhadan.phoneaccounting.remote.entities

data class SdCard(
    var inPhone: Boolean,
    val phoneName: String,
    val serialNumber: String,
    val size: String
)
