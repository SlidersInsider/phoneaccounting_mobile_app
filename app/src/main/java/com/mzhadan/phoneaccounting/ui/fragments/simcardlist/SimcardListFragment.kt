package com.mzhadan.phoneaccounting.ui.fragments.simcardlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mzhadan.phoneaccounting.R
import com.mzhadan.phoneaccounting.databinding.PhoneDetailsFragmentBinding
import com.mzhadan.phoneaccounting.databinding.SdcardListFragmentBinding
import com.mzhadan.phoneaccounting.databinding.SimcardListFragmentBinding
import com.mzhadan.phoneaccounting.ui.fragments.phonedetails.PhoneDetailsViewModel

class SimcardListFragment : Fragment() {

    private lateinit var binding: SimcardListFragmentBinding
    private val simcardListViewModel: SimcardListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimcardListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}