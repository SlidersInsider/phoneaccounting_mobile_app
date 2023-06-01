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
        phoneDetailsViewModel.phoneInfoList.observe(viewLifecycleOwner) { phoneInfoList ->
            if (phoneInfoList != null) {
                binding.loadingProgressBar.visibility = View.GONE
                binding.model.text = "Model: ${phoneInfoList[0].model}"
                binding.manufacturer.text = "Manufacturer: ${phoneInfoList[0].manufacturer}"
                binding.osVersion.text = "Os version: ${phoneInfoList[0].convertedOsVersion} (API ${phoneInfoList[0].osVersion})"
                binding.firmware.text = "Firmware: ${phoneInfoList[0].firmware}"
                binding.supportedArch.text = "Supported arch: ${phoneInfoList[0].supportedArch}"
                binding.simSlotsCount.text = "Sim slots count: ${phoneInfoList[0].simSlotsCount}"
                if (!phoneInfoList[0].simcard1.equals("-1")) {
                    phoneDetailsViewModel.getSimcardByNumber(phoneInfoList[0].simcard1, 1)
                }
                if (!phoneInfoList[0].simcard2.equals("-1")) {
                    phoneDetailsViewModel.getSimcardByNumber(phoneInfoList[0].simcard2, 2)
                }
                binding.sdSlotsCount.text = "Sd slots count: ${phoneInfoList[0].sdSlotsCount}"
                if (!phoneInfoList[0].sdcardSerialNumber.equals("-1")) {
                    phoneDetailsViewModel.getSdcardBySerialNumber(phoneInfoList[0].sdcardSerialNumber)
                } else {
                    binding.sdName.text = "Sd name: no sd"
                }
            } else {
                Toast.makeText(context, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sim1Data() {
        phoneDetailsViewModel.simcard1List.observe(viewLifecycleOwner) { simcardsList ->
            if (simcardsList != null) {
                binding.simcard1.text = "Simcard 1: ${simcardsList[0].number} (${simcardsList[0].operatorName})"
            } else {
                binding.simcard1.text = "Simcard 1: no sim"
            }
        }
    }

    private fun sim2Data() {
        phoneDetailsViewModel.simcard2List.observe(viewLifecycleOwner) { simcardsList ->
            if (simcardsList != null) {
                binding.simcard2.text = "Simcard 2: ${simcardsList[0].number} (${simcardsList[0].operatorName})"
            } else {
                binding.simcard2.text = "Simcard 2: no sim"
            }
        }
    }

    private fun sdData() {
        phoneDetailsViewModel.sdCardsList.observe(viewLifecycleOwner) { sdcardsList ->
            if (sdcardsList != null) {
                binding.sdName.text = "Sd card: ${sdcardsList[0].name}"
                binding.sdSize.text = "Size: ${sdcardsList[0].size}"
                binding.sdSerialNumber.text = "Serial number: ${sdcardsList[0].serialNumber}"
            } else {
                binding.sdName.text = "Sd card: no sd"
                binding.sdSize.text = ""
                binding.sdSerialNumber.text = ""
            }
        }
    }
}