package com.mzhadan.phoneaccounting.models

data class SimCardsInfo(
    val simCards: List<SimInfo>,
    val simSlotsCount: Int
)

data class SimInfo(
    val slotIndex: Int,
    val isInserted: Boolean,
    val simState: String,
    val simPhoneNumber: String
)