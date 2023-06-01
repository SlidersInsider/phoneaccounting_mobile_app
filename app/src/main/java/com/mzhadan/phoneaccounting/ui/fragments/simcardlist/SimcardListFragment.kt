package com.mzhadan.phoneaccounting.ui.fragments.simcardlist

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.common.CommonFunc
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import com.mzhadan.phoneaccounting.databinding.SdcardListFragmentBinding
import com.mzhadan.phoneaccounting.databinding.SimcardListFragmentBinding
import com.mzhadan.phoneaccounting.ui.adapters.NotificationListAdapter
import com.mzhadan.phoneaccounting.ui.adapters.PhoneListAdapter
import com.mzhadan.phoneaccounting.ui.adapters.SimCardListAdapter
import com.mzhadan.phoneaccounting.ui.fragments.phonedetails.PhoneDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimcardListFragment : Fragment() {

    private lateinit var binding: SimcardListFragmentBinding
    private lateinit var simCardListAdapter: SimCardListAdapter
    private val simcardListViewModel: SimcardListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimcardListFragmentBinding.inflate(inflater, container, false)
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
        simCardListAdapter = SimCardListAdapter(object: SimCardListAdapter.SimcardViewHolder.Callback {
            override fun onSimLock(isLocked: String, simcardId: Int, imageButton: ImageButton) {
                createLockDialog(isLocked, simcardId, imageButton)
            }

            override fun onDelete(simcardId: Int) {
                createDeleteDialog(simcardId)
            }
        })
    }

    private fun createLockDialog(isLocked: String, simcardId: Int, imageButton: ImageButton) {
        val alertDialog = AlertDialog.Builder(requireContext())
        val isLockedFlag = if (isLocked.equals("-1")) false else true
        alertDialog.apply {
            setTitle(if (isLockedFlag) "Unlock simcard" else "Lock simcard")
            setMessage(if (isLockedFlag) "Unlock simcard?" else "Lock simcard?")
            setPositiveButton("Yes") { dialog, _ ->
                if (CommonFunc.isNetworkConnected(context)) {
                    if (isLockedFlag) {
                        imageButton.setImageResource(R.drawable.card_simcard_unlock_icon)
                        simcardListViewModel.updateSimCardIsLocked(simcardId, "-1")
                    } else {
                        imageButton.setImageResource(R.drawable.card_simcard_lock_icon)
                        simcardListViewModel.updateSimCardIsLocked(simcardId, "1")
                    }
                } else {
                    Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
                }
                dialog.cancel()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        }.show()
    }

    private fun createDeleteDialog(simcardId: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle("Delete simcard")
            setMessage("Delete simcard?")
            setPositiveButton("Delete") { dialog, _ ->
                if (CommonFunc.isNetworkConnected(context)) {
                    simcardListViewModel.deleteSimCardById(simcardId)
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
        simcardListViewModel.getAllSimcards()

        simcardListViewModel.simcardsList.observe(viewLifecycleOwner) { simcardsList ->
            if (simcardsList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                simCardListAdapter.setData(simcardsList)
                binding.simcardRecyclerList.layoutManager = LinearLayoutManager(context)
                binding.simcardRecyclerList.adapter = simCardListAdapter
                if (isRefresh) {
                    binding.simcardSwipeRefresh.isRefreshing = false
                }
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRefreshLayout() {
        binding.simcardSwipeRefresh.setOnRefreshListener {
            binding.loadingProgressBar.visibility = View.VISIBLE
            getData(true)
        }
    }
}