package com.example.fetchrewards.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.model.FetchRewardsModel
import com.example.myapplication.databinding.RewardsItemGroupLayoutBinding

class GroupedItemAdapter : RecyclerView.Adapter<GroupedItemAdapter.GroupViewHolder>() {

    private var groupedItems: Map<Int, List<FetchRewardsModel>> = emptyMap()

    class GroupViewHolder(private val binding: RewardsItemGroupLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listId: Int, items: List<FetchRewardsModel>) {
            binding.listId = listId

            val itemAdapter = RewardsItemAdapter()
            itemAdapter.setItems(items)

            binding.groupRecyclerView.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = itemAdapter
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
        val listId = groupedItems.keys.toList()[position]
        val items = groupedItems[listId].orEmpty()
        holder.bind(listId, items)
    }

    override fun getItemCount(): Int = groupedItems.size

    fun setGroupedItems(newGroupedItems: Map<Int, List<FetchRewardsModel>>) {
        groupedItems = newGroupedItems
        notifyDataSetChanged()
    }
}
