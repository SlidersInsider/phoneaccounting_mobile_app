package com.mzhadan.phoneaccounting.remote.entities

data class Notification(
    val notificationId: Int,
    val notificationText: String,
    val packageName: String
)