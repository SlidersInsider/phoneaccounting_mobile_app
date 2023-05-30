package com.mzhadan.phoneaccounting.ui.fragments.phonedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneDetailsFragment : Fragment() {

    private lateinit var binding: PhoneDetailsFragmentBinding
    private val phoneDetailsViewModel: PhoneDetailsViewModel by viewModels()

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
        getData(phoneId)

    }

    private fun getData(phoneId: Int) {
        phoneDetailsViewModel.getPhoneInfoById(phoneId)

        phoneDetailsViewModel.phoneDetailsList.observe(viewLifecycleOwner) { phoneInfoList ->
            if (phoneInfoList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                binding.model.text = "Model: ${phoneInfoList[0].model}"
                binding.manufacturer.text = "Manufacturer: ${phoneInfoList[0].manufacturer}"
                binding.osVersion.text = "Os version: ${phoneInfoList[0].osVersion}"
                binding.firmware.text = "Firmware: ${phoneInfoList[0].firmware}"
                binding.supportedArch.text = "Supported arch: ${phoneInfoList[0].supportedArch}"
                binding.simSlotsCount.text = "Sim slots count: ${phoneInfoList[0].simSlotsCount}"
                binding.simcard1.text = "Simcard 1: ${phoneInfoList[0].simcard1}"
                binding.simcard2.text = "Simcard 2: ${phoneInfoList[0].simcard2}"
                binding.sdSlotsCount.text = "Sd slots count: ${phoneInfoList[0].sdSlotsCount}"
                binding.sdcard.text = "Sdcard: ${phoneInfoList[0].sdcard}"
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}