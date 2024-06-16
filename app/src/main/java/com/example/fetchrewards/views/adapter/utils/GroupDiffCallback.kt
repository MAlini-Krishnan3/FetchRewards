package com.example.fetchrewards.views.adapter.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.fetchrewards.model.FetchRewardsModel

class GroupDiffCallback : DiffUtil.ItemCallback<Pair<Int, List<FetchRewardsModel>>>() {
    override fun areItemsTheSame(
        oldItem: Pair<Int, List<FetchRewardsModel>>,
        newItem: Pair<Int, List<FetchRewardsModel>>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<Int, List<FetchRewardsModel>>,
        newItem: Pair<Int, List<FetchRewardsModel>>
    ): Boolean {
        return oldItem == newItem
    }
}