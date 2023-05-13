package com.mzhadan.phoneaccounting.ui.fragments.phonedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import com.mzhadan.phoneaccounting.databinding.PhoneListFragmentBinding
import com.mzhadan.phoneaccounting.ui.fragments.phonelist.PhoneListViewModel

class PhoneDetailsFragment : Fragment() {

    lateinit var binding: PhoneDetailsFragmentBinding
    private lateinit var phoneDetailsViewModel: PhoneDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhoneDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneId: Int = requireArguments().getInt("phoneId")
        setupViewModel(phoneId)

    }

    private fun setupViewModel(phoneId: Int) {
        phoneDetailsViewModel = ViewModelProvider(this)[PhoneDetailsViewModel::class.java]
        phoneDetailsViewModel.getPhoneInfoById(phoneId)

        phoneDetailsViewModel.phoneDetailsList.observe(viewLifecycleOwner) { phoneInfoList ->
            if (phoneInfoList != null) {
                val phoneName = "${phoneInfoList[0].manufacturer} ${phoneInfoList[0].model}"
                binding.phoneNameText.text = phoneName
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}