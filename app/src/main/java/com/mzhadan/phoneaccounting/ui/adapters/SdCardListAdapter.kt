package com.mzhadan.phoneaccounting.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mzhadan.phoneaccounting.databinding.SdcardListElementBinding
import com.mzhadan.phoneaccounting.remote.entities.SdCard

class SdCardListAdapter(
    private val callback: SdcardViewHolder.Callback
): RecyclerView.Adapter<SdCardListAdapter.SdcardViewHolder>() {

    private val sdcardsListData = ArrayList<SdCard>()

    fun setData(newSdcardsListData: List<SdCard>) {
        sdcardsListData.clear()
        sdcardsListData.addAll(newSdcardsListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SdcardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val sdcardListElementBinding = SdcardListElementBinding.inflate(layoutInflater, parent, false)
        return SdcardViewHolder(sdcardListElementBinding, callback = callback)
    }

    override fun onBindViewHolder(holder: SdcardViewHolder, position: Int) {
        holder.bind(sdcardsListData[position])
    }

    override fun getItemCount(): Int {
        return sdcardsListData.size
    }

    class SdcardViewHolder(
        private val binding: SdcardListElementBinding,
        private val callback: Callback
    ) : RecyclerView.ViewHolder(binding.root) {

        interface Callback{
            fun onDelete(sdcardId: Int)
        }

        fun bind(sdCard: SdCard) {
            binding.sdName.text = sdCard.name
            binding.sdSize.text = sdCard.size
            binding.sdSerialNumber.text = sdCard.serialNumber
            binding.root.setOnLongClickListener {
                callback.onDelete(sdCard.sdcardId)
                true
            }
        }
    }
}