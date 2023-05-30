package com.mzhadan.phoneaccounting.ui.fragments.notificationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.common.CommonFunc
import com.mzhadan.phoneaccounting.databinding.NotificationListFragmentBinding
import com.mzhadan.phoneaccounting.ui.adapters.NotificationListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationListFragment : Fragment() {

    private lateinit var binding: NotificationListFragmentBinding
    private lateinit var notificationListAdapter: NotificationListAdapter
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

        setupRecyclerView()
        if (CommonFunc.isNetworkConnected(context)) {
            getData(false)
        } else {
            Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
        }
        setupRefreshLayout()
    }

    private fun setupRecyclerView() {
        notificationListAdapter = NotificationListAdapter()
    }

    private fun getData(isRefresh: Boolean) {
        notificaionListViewModel.getAllNotifications()

        notificaionListViewModel.notificationsList.observe(viewLifecycleOwner) { notificationList ->
            if (notificationList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                notificationListAdapter.setData(notificationList)
                val layoutManager = LinearLayoutManager(this.context)
                layoutManager.stackFromEnd = true
                layoutManager.reverseLayout = true
                binding.notificationRecyclerList.layoutManager = layoutManager
                binding.notificationRecyclerList.adapter = notificationListAdapter
                if (isRefresh) {
                    binding.notificationSwipeRefresh.isRefreshing = false
                }
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRefreshLayout() {
        binding.notificationSwipeRefresh.setOnRefreshListener {
            binding.loadingProgressBar.visibility = View.VISIBLE
            getData(true)
        }
    }
}