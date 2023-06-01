package com.mzhadan.phoneaccounting.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mzhadan.phoneaccounting.databinding.SimcardListElementBinding
import com.mzhadan.phoneaccounting.remote.entities.SimCard


class SimCardListAdapter(
    private val callback: SimcardViewHolder.Callback
): RecyclerView.Adapter<SimCardListAdapter.SimcardViewHolder>() {

    private val simcardsListData = ArrayList<SimCard>()

    fun setData(newSimcardsListData: List<SimCard>) {
        simcardsListData.clear()
        simcardsListData.addAll(newSimcardsListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimcardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val simcardListElementBinding = SimcardListElementBinding.inflate(layoutInflater, parent, false)
        return SimcardViewHolder(simcardListElementBinding, callback = callback)
    }

    override fun onBindViewHolder(holder: SimcardViewHolder, position: Int) {
        holder.bind(simcardsListData[position])
    }

    override fun getItemCount(): Int {
        return simcardsListData.size
    }

    class SimcardViewHolder(
        private val binding: SimcardListElementBinding,
        private val callback: Callback
    ) : RecyclerView.ViewHolder(binding.root) {

        interface Callback{
            fun onSimLock(isLocked: String, simcardId: Int)
            fun onDelete(simcardId: Int)
        }

        fun bind(simcard: SimCard) {
            binding.phone1.text = "${simcard.number} (${simcard.operatorName})"
            binding.lockSimcardBtn.setOnClickListener {
                callback.onSimLock(simcard.isLocked, simcard.simcardId)
            }
            binding.root.setOnLongClickListener {
                callback.onDelete(simcard.simcardId)
                true
            }
        }
    }
}