package com.mzhadan.phoneaccounting.models.entities

data class SimCard(
    var inPhone: Boolean,
    val phoneName: String,
    val number: String,
    var accounts: String
)
