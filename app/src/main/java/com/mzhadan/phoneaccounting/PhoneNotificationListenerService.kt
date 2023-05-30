package com.mzhadan.phoneaccounting

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.mzhadan.phoneaccounting.common.CommonFunc
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
    val needPackagesList = listOf<String>("com.whatsapp", "org.telegram.messenger", "com.viber.voip", "com.samsung.android.messaging")

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val notificationText = sbn.notification.extras.getCharSequence("android.text").toString()
        val packageName = sbn.packageName
        val title = sbn.notification.extras.getString("android.title")

        if (notificationText != null) {
            println("Notification listener package -> $packageName")
            println("Notification listener title -> $title")
            println("Notification listener text -> $notificationText")
            if (needPackagesList.contains(packageName)) {
                val notification = Notification(0, title!!, notificationText, packageName)
                if (CommonFunc.isNetworkConnected(applicationContext)) {
                    sendNotificationOnServer(notification)
                }
            }
        }
    }

    fun sendNotificationOnServer(notification: Notification) {
        coroutineScope.launch {
            notificationsRepository.addNewNotification(notification)
        }
    }
}