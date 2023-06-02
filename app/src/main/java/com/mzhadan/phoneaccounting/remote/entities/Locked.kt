package com.mzhadan.phoneaccounting.remote.entities

import com.google.gson.annotations.SerializedName

data class Locked(
    @SerializedName("locked")
    val locked: Boolean
)
