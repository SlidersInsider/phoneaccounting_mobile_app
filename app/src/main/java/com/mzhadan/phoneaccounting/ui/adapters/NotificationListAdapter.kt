package com.mzhadan.phoneaccounting.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mzhadan.phoneaccounting.databinding.NotificationListElementBinding
import com.mzhadan.phoneaccounting.remote.entities.Notification

class NotificationListAdapter: RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {

    private val notificationsListData = ArrayList<Notification>()

    fun setData(newNotificationListData: List<Notification>) {
        notificationsListData.clear()
        notificationsListData.addAll(newNotificationListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val notificationListElementBinding = NotificationListElementBinding.inflate(layoutInflater, parent, false)
        return NotificationViewHolder(notificationListElementBinding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notificationsListData[position])
    }

    override fun getItemCount(): Int {
        return notificationsListData.size
    }

    class NotificationViewHolder(
        private val binding: NotificationListElementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification) {
            binding.notificationPackageName.text = notification.packageName
            binding.notificationTitle.text = notification.title
            binding.notificationText.text = notification.notificationText
        }
    }
}
