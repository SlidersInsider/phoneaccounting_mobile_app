package com.mzhadan.phoneaccounting.remote.entities

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("id")
    val notificationId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("text")
    val notificationText: String,
    @SerializedName("package_name")
    val packageName: String
)