package com.mzhadan.phoneaccounting.remote.entities

data class SimCard(
    var simcardId: Int,
    var operatorName: String,
    var number: String,
    var isLocked: String,
    var inPhone: String
)
