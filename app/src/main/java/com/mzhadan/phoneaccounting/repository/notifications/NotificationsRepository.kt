package com.mzhadan.phoneaccounting.repository.notifications

import com.mzhadan.phoneaccounting.remote.entities.Notification
import retrofit2.Response

interface NotificationsRepository {
    suspend fun getAllNotifications(): List<Notification>
    suspend fun getNotificationById(notificationId: Int): Notification
    suspend fun addNewNotification(notification: Notification): Response<Notification>
}