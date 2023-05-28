package com.mzhadan.phoneaccounting.ui.fragments.notificationlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.remote.entities.Notification
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificaionListViewModel @Inject constructor(
    private val notificationsRepository: NotificationsRepository
): ViewModel() {

    var notificationsList = MutableLiveData<List<Notification>>()

    fun getAllNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            val notificationsResponse = notificationsRepository.getAllNotifications()
            notificationsList.postValue(notificationsResponse)
        }
    }

    fun addNewNotification(notification: Notification) {
        viewModelScope.launch(Dispatchers.IO) {
            notificationsRepository.addNewNotification(notification)
        }
    }

}