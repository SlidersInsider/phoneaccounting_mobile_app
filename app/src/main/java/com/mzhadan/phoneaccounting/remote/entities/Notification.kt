package com.mzhadan.phoneaccounting.remote.entities

data class Notification(
    val notificationId: Int,
    val title: String,
    val notificationText: String,
    val packageName: String
)