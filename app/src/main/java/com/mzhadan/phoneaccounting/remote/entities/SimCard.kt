package com.mzhadan.phoneaccounting.remote.entities

data class SimCard(
    var inPhone: Boolean,
    val phoneName: String,
    val number: String,
    var accounts: String
)
