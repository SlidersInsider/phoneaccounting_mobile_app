package com.mzhadan.phoneaccounting.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mzhadan.phoneaccounting.databinding.PhoneInfoListElementBinding
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo

class PhoneListAdapter(private val callback: PhoneInfoViewHolder.Callback): RecyclerView.Adapter<PhoneListAdapter.PhoneInfoViewHolder>() {

    private val phoneInfoListData = ArrayList<PhoneInfo>()

    fun setData(newCryptoListData: List<PhoneInfo>) {
        phoneInfoListData.clear()
        phoneInfoListData.addAll(newCryptoListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val phoneInfoListElementBinding = PhoneInfoListElementBinding.inflate(layoutInflater, parent, false)
        return PhoneInfoViewHolder(phoneInfoListElementBinding, callback = callback)
    }

    override fun onBindViewHolder(holder: PhoneInfoViewHolder, position: Int) {
        holder.bind(phoneInfoListData[position])
    }

    override fun getItemCount(): Int {
        return phoneInfoListData.size
    }

    class PhoneInfoViewHolder(
        private val binding: PhoneInfoListElementBinding,
        private val callback: Callback
        ) : RecyclerView.ViewHolder(binding.root) {

        interface Callback{
            fun onPhoneInfoClicked(phoneInfo: PhoneInfo)
        }

        fun bind(phoneInfo: PhoneInfo){
            binding.phoneName.text = "${phoneInfo.manufacturer} ${phoneInfo.model}"
            binding.androidVersion.text = "${phoneInfo.osVersion}"
            binding.phone1.text = phoneInfo.simcard1
            binding.phone2.text = phoneInfo.simcard2
            binding.sdcard.text = phoneInfo.sdcard
            binding.root.setOnClickListener {
                callback.onPhoneInfoClicked(phoneInfo)
            }
        }
    }

}