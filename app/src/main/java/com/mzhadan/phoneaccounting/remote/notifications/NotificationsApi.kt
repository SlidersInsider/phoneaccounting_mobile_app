package com.mzhadan.phoneaccounting.remote.notifications

import com.mzhadan.phoneaccounting.remote.entities.Notification
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NotificationsApi {
    @GET("notifications/")
    suspend fun getAllNotifications(): List<Notification>

    @GET("notifications/{notificationId}/")
    suspend fun getNotificationById(
        @Path("notificationId")
        notificationId: Int
    ): Notification

    @POST("notifications/")
    suspend fun addNewNotification(
        @Body
        notification: Notification
    ): Response<Notification>
}