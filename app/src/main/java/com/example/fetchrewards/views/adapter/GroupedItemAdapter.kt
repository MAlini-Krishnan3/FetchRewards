package com.example.fetchrewards.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.model.FetchRewardsModel
import com.example.fetchrewards.views.adapter.utils.GroupDiffCallback
import com.example.myapplication.R
import com.example.myapplication.databinding.RewardsItemGroupLayoutBinding
import com.example.myapplication.databinding.RewardsItemLayoutBinding

class GroupedItemAdapter :
    ListAdapter<Pair<Int, List<FetchRewardsModel>>, GroupedItemAdapter.GroupViewHolder>(
        GroupDiffCallback()
    ) {

    private var groupedItems: Map<Int, List<FetchRewardsModel>> = emptyMap()

    class GroupViewHolder(private val binding: RewardsItemGroupLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listId: Int, items: List<FetchRewardsModel>) {
            binding.listId = listId

            // Clear any existing rows
            binding.groupTable.removeAllViews()

            // Inflate and add the table header row
            val headerRow = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.table_header_row, binding.groupTable, false)
            binding.groupTable.addView(headerRow)

            // Inflate and add each item as a row in the table
            val inflater = LayoutInflater.from(binding.root.context)
            if (items != null && items.isNotEmpty()) {
                items.forEach { item ->
                    val itemBinding =
                        RewardsItemLayoutBinding.inflate(inflater, binding.groupTable, false)
                    itemBinding.item = item
                    binding.groupTable.addView(itemBinding.root)
                }
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RewardsItemGroupLayoutBinding.inflate(layoutInflater, parent, false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val (listId, items) = getItem(position)
        holder.bind(listId, items)
    }
}
