package com.mzhadan.phoneaccounting

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.mzhadan.phoneaccounting.remote.entities.Notification
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PhoneNotificationListenerService: NotificationListenerService() {

    @Inject
    lateinit var notificationsRepository: NotificationsRepository
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val notificationText = sbn.notification?.extras?.getCharSequence("android.text")?.toString()
        val packageName = sbn.packageName

        if (notificationText != null) {
            println("Notification listener package -> $packageName")
            println("Notification listener text -> $notificationText")
            if (packageName == "whatsapp") {
                val notification = Notification(0, notificationText, packageName)
                sendNotificationOnServer(notification)
            }

        }
    }

    fun sendNotificationOnServer(notification: Notification) {
        coroutineScope.launch {
            notificationsRepository.addNewNotification(notification)
        }
    }
}