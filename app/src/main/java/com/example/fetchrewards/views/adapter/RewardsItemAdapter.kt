package com.example.fetchrewards.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.model.FetchRewardsModel
import com.example.fetchrewards.views.adapter.utils.ItemDiffCallback
import com.example.myapplication.databinding.RewardsItemLayoutBinding

class RewardsItemAdapter : ListAdapter<FetchRewardsModel, RewardsItemAdapter.ItemViewHolder>(
    ItemDiffCallback()
)  {

    private var items: List<FetchRewardsModel> = emptyList()

    class ItemViewHolder(private val binding: RewardsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FetchRewardsModel) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RewardsItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

}