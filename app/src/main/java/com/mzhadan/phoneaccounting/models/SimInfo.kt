package com.mzhadan.phoneaccounting.models

data class SimCardsInfo(
    val simCards: List<SimInfo>,
    val simSlotsCount: Int
)

data class SimInfo(
    val slotIndex: Int,
    val isInserted: Boolean,
    val state: String,
    val number: String,
    val provider: String
)