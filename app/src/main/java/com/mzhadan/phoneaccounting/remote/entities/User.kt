package com.mzhadan.phoneaccounting.remote.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user")
    val user: String
)
