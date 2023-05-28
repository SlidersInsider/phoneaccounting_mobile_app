package com.mzhadan.phoneaccounting.repository.notifications

import com.mzhadan.phoneaccounting.remote.entities.Notification
import com.mzhadan.phoneaccounting.remote.notifications.NotificationsApi
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val notificationsApi: NotificationsApi
): NotificationsRepository {

    override suspend fun getAllNotifications(): List<Notification> {
        return notificationsApi.getAllNotifications()
    }

    override suspend fun getNotificationById(notificationId: Int): List<Notification> {
        return notificationsApi.getNotificationById(notificationId)
    }

    override suspend fun addNewNotification(notification: Notification) {
       return notificationsApi.addNewNotification(notification)
    }
}