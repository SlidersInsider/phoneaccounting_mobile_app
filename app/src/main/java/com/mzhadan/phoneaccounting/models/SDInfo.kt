package com.mzhadan.phoneaccounting.models

data class SDInfo(
    val haveSlot: Boolean,
    val isInserted: Boolean,
    val sdCardSize: Int,
    val serialNumber: String,
)
