package com.mzhadan.phoneaccounting.ui.fragments.sdcardlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import com.mzhadan.phoneaccounting.databinding.SdcardListFragmentBinding
import com.mzhadan.phoneaccounting.ui.fragments.phonedetails.PhoneDetailsViewModel

class SdcardListFragment : Fragment() {

    private lateinit var binding: SdcardListFragmentBinding
    private val sdcardListViewModel: SdcardListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SdcardListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}