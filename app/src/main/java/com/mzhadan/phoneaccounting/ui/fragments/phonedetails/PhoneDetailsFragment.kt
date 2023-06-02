package com.mzhadan.phoneaccounting.ui.fragments.phonedetails

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mzhadan.phoneaccounting.R
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

        phoneData()
        sim1Data()
        sim2Data()
        sdData()
    }

    private fun phoneData() {
        phoneDetailsViewModel.phoneInfoElem.observe(viewLifecycleOwner) { phoneInfo ->
            if (phoneInfo != null) {
                binding.loadingProgressBar.visibility = View.GONE
                binding.model.text = "Model: ${phoneInfo.model}"
                binding.manufacturer.text = "Manufacturer: ${phoneInfo.manufacturer}"
                binding.osVersion.text = "Os version: ${phoneInfo.convertedOsVersion} (API ${phoneInfo.osVersion})"
                binding.firmware.text = "Firmware: ${phoneInfo.firmware}"
                binding.supportedArch.text = "Supported arch: ${phoneInfo.supportedArch}"
                binding.simSlotsCount.text = "Sim slots count: ${phoneInfo.simSlotsCount}"
                if (!phoneInfo.simcard1.equals("-1")) {
                    phoneDetailsViewModel.getSimcardByNumber(phoneInfo.simcard1, 1)
                }
                if (!phoneInfo.simcard2.equals("-1")) {
                    phoneDetailsViewModel.getSimcardByNumber(phoneInfo.simcard2, 2)
                }
                binding.sdSlotsCount.text = "Sd slots count: ${phoneInfo.sdSlotsCount}"
                if (!phoneInfo.sdcardSerialNumber.equals("-1")) {
                    phoneDetailsViewModel.getSdcardBySerialNumber(phoneInfo.sdcardSerialNumber)
                } else {
                    binding.sdName.text = "Sd name: no sd"
                }
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sim1Data() {
        phoneDetailsViewModel.simcard1Elem.observe(viewLifecycleOwner) { simcard ->
            if (simcard != null) {
                binding.simcard1.text = "Simcard 1: ${simcard[0].number} (${simcard[0].operatorName})"
            } else {
                binding.simcard1.text = "Simcard 1: no sim"
            }
        }
    }

    private fun sim2Data() {
        phoneDetailsViewModel.simcard2Elem.observe(viewLifecycleOwner) { simcard ->
            if (simcard != null) {
                binding.simcard2.text = "Simcard 2: ${simcard[0].number} (${simcard[0].operatorName})"
            } else {
                binding.simcard2.text = "Simcard 2: no sim"
            }
        }
    }

    private fun sdData() {
        phoneDetailsViewModel.sdCardElem.observe(viewLifecycleOwner) { sdcard ->
            if (sdcard != null) {
                binding.sdName.text = "Sd card: ${sdcard[0].name}"
                binding.sdSize.text = "Size: ${sdcard[0].size}"
                binding.sdSerialNumber.text = "Serial number: ${sdcard[0].serialNumber}"
            } else {
                binding.sdName.text = "Sd card: no sd"
                binding.sdSize.text = ""
                binding.sdSerialNumber.text = ""
            }
        }
    }
}