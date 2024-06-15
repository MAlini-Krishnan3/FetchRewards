package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.model.FetchRewardsModel
import com.example.myapplication.databinding.RewardsItemLayoutBinding

class GroupedItemAdapter : RecyclerView.Adapter<GroupedItemAdapter.ItemViewHolder>() {

    private var groupedItems: Map<Int, List<FetchRewardsModel>> = emptyMap()

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
        val items = groupedItems.values.flatten()
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = groupedItems.values.flatten().size

    fun setItems(newGroupedItems: Map<Int, List<FetchRewardsModel>>) {
        groupedItems = newGroupedItems
        notifyDataSetChanged()
    }
}
