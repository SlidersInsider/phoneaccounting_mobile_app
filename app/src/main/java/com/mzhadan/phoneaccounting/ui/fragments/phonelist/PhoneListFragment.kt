package com.mzhadan.phoneaccounting.ui.fragments.phonelist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.databinding.PhoneListFragmentBinding
import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.ui.adapters.PhoneListAdapter
import com.mzhadan.phoneaccounting.ui.fragments.phonedetails.PhoneDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneListFragment : Fragment() {

    private lateinit var binding: PhoneListFragmentBinding
    private lateinit var phoneListAdapter: PhoneListAdapter
    private val phoneListViewModel: PhoneListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhoneListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        if (isNetworkConnected()) {
            getStartRemoteData(false)
        } else {
//            binding.loadingProgressBar.visibility = View.GONE
//            binding.noInternetText.visibility = View.VISIBLE
            getStartLocalData(false)
        }

        setupRefreshLayout()
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    private fun setupRecyclerView() {
        phoneListAdapter = PhoneListAdapter(object: PhoneListAdapter.PhoneInfoViewHolder.Callback {
            override fun onPhoneInfoClicked(phoneInfo: PhoneInfo) {
                if (isNetworkConnected()) {
                    val phoneDetailsFragment = PhoneDetailsFragment()
                    val phoneBundle = Bundle()
                    phoneBundle.putInt("phoneId", phoneInfo.phoneId)
                    phoneDetailsFragment.arguments = phoneBundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.mainFragmentContainer, phoneDetailsFragment, "PhoneDetailsFragment")
                        .addToBackStack("PhoneDetailsFragment for ${phoneInfo.phoneId}")
                        .commit()
                } else {
                    Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onEditUserName(name: String) {
                Toast.makeText(context, "edit user clicked!", Toast.LENGTH_SHORT).show()
            }

            override fun onLongPhoneCardClicked(phoneInfo: PhoneInfo) {
                Toast.makeText(context, "long card clicked!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getStartRemoteData(isRefresh: Boolean) {
        phoneListViewModel.remoteGetAllPhoneInfo()

        phoneListViewModel.phoneInfoList.observe(viewLifecycleOwner) { phoneInfoList ->
            if (phoneInfoList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                phoneListAdapter.setData(phoneInfoList)
                binding.phoneInfoRecyclerList.layoutManager = LinearLayoutManager(this.context)
                binding.phoneInfoRecyclerList.adapter = phoneListAdapter
                phoneListViewModel.localSaveAllPhoneInfo(phoneInfoList)
                if (isRefresh) {
                    binding.phoneInfoSwipeRefresh.isRefreshing = false
                }
            } else {
                Toast.makeText(context, "Failed to fetch remote data!", Toast.LENGTH_SHORT).show()
            }
        }
        Toast.makeText(context, "Remote data", Toast.LENGTH_SHORT).show()
    }

    private fun getStartLocalData(isRefresh: Boolean) {
        phoneListViewModel.localGetAllPhoneInfo()

        phoneListViewModel.localPhoneInfoList.observe(viewLifecycleOwner) { phoneInfoList ->
            if (phoneInfoList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                phoneListAdapter.setData(LocalPhoneInfo.mapToPhoneInfo(phoneInfoList))
                binding.phoneInfoRecyclerList.layoutManager = LinearLayoutManager(this.context)
                binding.phoneInfoRecyclerList.adapter = phoneListAdapter
                if (isRefresh) {
                    binding.phoneInfoSwipeRefresh.isRefreshing = false
                }
            } else {
                Toast.makeText(context, "Failed to fetch remote data!", Toast.LENGTH_SHORT).show()
            }
        }
        Toast.makeText(context, "Local data", Toast.LENGTH_SHORT).show()
    }

    private fun setupRefreshLayout() {
        binding.phoneInfoSwipeRefresh.setOnRefreshListener {
            binding.noInternetText.visibility = View.GONE
            binding.loadingProgressBar.visibility = View.VISIBLE
            if (isNetworkConnected()) {
                getStartRemoteData(true)
            } else {
                getStartLocalData(true)
//                binding.phoneInfoSwipeRefresh.isRefreshing = false
//                binding.loadingProgressBar.visibility = View.GONE
//                binding.noInternetText.visibility = View.VISIBLE
            }
        }
    }
}