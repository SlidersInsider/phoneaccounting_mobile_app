package com.mzhadan.phoneaccounting.repository.notifications

import com.mzhadan.phoneaccounting.remote.entities.Notification

interface NotificationsRepository {
    suspend fun getAllNotifications(): List<Notification>
    suspend fun getNotificationById(notificationId: Int): List<Notification>
    suspend fun addNewNotification(notification: Notification)
}