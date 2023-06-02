package com.mzhadan.phoneaccounting.remote.entities

import com.google.gson.annotations.SerializedName

data class SimCard(
    @SerializedName("id")
    var simcardId: Int,
    @SerializedName("operator_name")
    var operatorName: String,
    @SerializedName("number")
    var number: String,
    @SerializedName("locked")
    var locked: Boolean,
    @SerializedName("in_phone")
    var inPhone: Boolean
)
