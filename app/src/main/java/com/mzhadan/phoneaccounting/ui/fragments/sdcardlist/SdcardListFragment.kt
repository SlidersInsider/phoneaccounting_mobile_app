package com.mzhadan.phoneaccounting.ui.fragments.sdcardlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.common.CommonFunc
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import com.mzhadan.phoneaccounting.databinding.SdcardListFragmentBinding
import com.mzhadan.phoneaccounting.ui.adapters.SdCardListAdapter
import com.mzhadan.phoneaccounting.ui.adapters.SimCardListAdapter
import com.mzhadan.phoneaccounting.ui.fragments.phonedetails.PhoneDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SdcardListFragment : Fragment() {

    private lateinit var binding: SdcardListFragmentBinding
    lateinit var sdCardListAdapter: SdCardListAdapter
    private val sdcardListViewModel: SdcardListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SdcardListFragmentBinding.inflate(inflater, container, false)
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
        sdCardListAdapter = SdCardListAdapter(object: SdCardListAdapter.SdcardViewHolder.Callback {
            override fun onDelete(sdcardId: Int) {
                createDeleteDialog(sdcardId)
            }
        })
    }

    private fun createDeleteDialog(sdcardId: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle("Delete sdcard")
            setMessage("Delete sdcard?")
            setPositiveButton("Delete") { dialog, _ ->
                if (CommonFunc.isNetworkConnected(context)) {
                    sdcardListViewModel.deleteSdCardById(sdcardId)
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
        sdcardListViewModel.getAllSdCards()

        sdcardListViewModel.sdcardsList.observe(viewLifecycleOwner) { simcardsList ->
            if (simcardsList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                sdCardListAdapter.setData(simcardsList)
                binding.sdcardRecyclerList.layoutManager = LinearLayoutManager(context)
                binding.sdcardRecyclerList.adapter = sdCardListAdapter
                if (isRefresh) {
                    binding.sdcardSwipeRefresh.isRefreshing = false
                }
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRefreshLayout() {
        binding.sdcardSwipeRefresh.setOnRefreshListener {
            binding.loadingProgressBar.visibility = View.VISIBLE
            getData(true)
        }
    }
}