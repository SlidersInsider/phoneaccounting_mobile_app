package com.mzhadan.phoneaccounting.ui.fragments.sdcardlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mzhadan.phoneaccounting.R

class SdcardListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sdcard_list_fragment, container, false)
    }
}