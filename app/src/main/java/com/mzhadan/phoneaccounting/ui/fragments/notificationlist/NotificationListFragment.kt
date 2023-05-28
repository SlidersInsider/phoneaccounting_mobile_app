package com.mzhadan.phoneaccounting.ui.fragments.notificationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.databinding.NotificationListFragmentBinding
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationListFragment : Fragment() {

    private lateinit var binding: NotificationListFragmentBinding
    private val notificaionListViewModel: NotificaionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotificationListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        notificaionListViewModel.getAllNotifications()

        notificaionListViewModel.notificationsList.observe(viewLifecycleOwner) { notificationList ->
            if (notificationList != null) {
                binding.notificationText.text = notificationList[0].notificationText
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}