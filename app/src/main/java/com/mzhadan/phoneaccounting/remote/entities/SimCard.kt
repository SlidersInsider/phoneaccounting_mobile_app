package com.mzhadan.phoneaccounting.remote.entities

data class SimCard(
    var simcardId: Int,
    var operatorName: String,
    var number: String,
    var locked: String,
    var inPhone: String
)
