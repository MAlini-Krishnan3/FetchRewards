package com.example.fetchrewards.views.adapter.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.fetchrewards.model.FetchRewardsModel

class ItemDiffCallback : DiffUtil.ItemCallback<FetchRewardsModel>() {
    override fun areItemsTheSame(oldItem: FetchRewardsModel, newItem: FetchRewardsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FetchRewardsModel,
        newItem: FetchRewardsModel
    ): Boolean {
        return oldItem == newItem
    }
}