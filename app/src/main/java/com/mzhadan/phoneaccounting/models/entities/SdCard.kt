package com.mzhadan.phoneaccounting.models.entities

data class SdCard(
    var inPhone: Boolean,
    val phoneName: String,
    val serialNumber: String,
    val size: String
)
