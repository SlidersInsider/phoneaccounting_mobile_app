package com.mzhadan.phoneaccounting.ui.fragments.phonelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.databinding.PhoneListFragmentBinding
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.ui.adapters.PhoneListAdapter
import com.mzhadan.phoneaccounting.ui.fragments.phonedetails.PhoneDetailsFragment

class PhoneListFragment : Fragment() {

    lateinit var binding: PhoneListFragmentBinding
    private lateinit var phoneListAdapter: PhoneListAdapter
    private lateinit var phoneListViewModel: PhoneListViewModel

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
        setupViewModel()
    }

    private fun setupRecyclerView() {
        phoneListAdapter = PhoneListAdapter(object: PhoneListAdapter.PhoneInfoViewHolder.Callback {
            override fun onPhoneInfoClicked(phoneInfo: PhoneInfo) {
                val phoneDetailsFragment = PhoneDetailsFragment()
                val phoneBundle = Bundle()
                phoneBundle.putInt("phoneId", phoneInfo.phoneId)
                phoneDetailsFragment.arguments = phoneBundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFragmentContainer, phoneDetailsFragment, "PhoneDetailsFragment")
                    .addToBackStack("PhoneDetailsFragment for ${phoneInfo.phoneId}")
                    .commit()
            }
        })
    }

    private fun setupViewModel() {
        phoneListViewModel = ViewModelProvider(this)[PhoneListViewModel::class.java]

        phoneListViewModel.phoneInfoList.observe(viewLifecycleOwner) { phoneInfoList ->
            if (phoneInfoList != null) {
                phoneListAdapter.setData(phoneInfoList)
                binding.phoneInfoRecyclerList.layoutManager = LinearLayoutManager(this.context)
                binding.phoneInfoRecyclerList.adapter = phoneListAdapter
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}