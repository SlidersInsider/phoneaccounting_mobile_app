package com.mzhadan.phoneaccounting.ui.fragments.phonelist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginStart
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.common.CommonFunc.Companion.isNetworkConnected
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

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getData(false)
        setupRefreshLayout()
    }

    private fun setupRecyclerView() {
        phoneListAdapter = PhoneListAdapter(object: PhoneListAdapter.PhoneInfoViewHolder.Callback {
            override fun onPhoneInfoClicked(phoneInfo: PhoneInfo) {
                if (isNetworkConnected(context)) {
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

            override fun onEditUserName(phoneId: Int, name: String) {
                createEditDialog(phoneId, name)
            }

            override fun onLongPhoneCardClicked(phoneId: Int) {
                createDeleteDialog(phoneId)
            }
        })
    }

    private fun createEditDialog(phoneId: Int, name: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle("Update username")
            val editText =  EditText(requireContext())
            editText.setText(name)
            setMessage("Enter new username")
            setView(editText)
            setPositiveButton("Edit") { dialog, _ ->
                if (isNetworkConnected(context)) {
                    phoneListViewModel.updatePhoneInfoUser(phoneId, editText.text.toString())
                } else {
                    Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
                }
                dialog.cancel()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        }.show()
    }

    private fun createDeleteDialog(phoneId: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle("Delete phone info")
            setMessage("Delete phone info?")
            setPositiveButton("Delete") { dialog, _ ->
                if (isNetworkConnected(context)) {
                    phoneListViewModel.deletePhoneInfoById(phoneId)
                } else {
                    Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
                }
                dialog.cancel()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        }.show()
    }

    private fun getData(isRefresh: Boolean) {
        if (isNetworkConnected(context)) {
            getRemoteData(isRefresh)
        } else {
            Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
            getLocalData(isRefresh)
        }
    }

    private fun getRemoteData(isRefresh: Boolean) {
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
    }

    private fun getLocalData(isRefresh: Boolean) {
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
    }

    private fun setupRefreshLayout() {
        binding.phoneInfoSwipeRefresh.setOnRefreshListener {
            binding.loadingProgressBar.visibility = View.VISIBLE
            getData(true)
        }
    }
}