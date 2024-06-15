package com.example.fetchrewards.datarepo

import com.example.fetchrewards.model.FetchRewardsModel
import com.example.fetchrewards.service.FetchRewardsApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchRewardsDataRepo @Inject constructor(private val apiService: FetchRewardsApi) {

    fun fetchRewardItems(): Single<List<FetchRewardsModel>> {
        return apiService.getRewardsData()
    }
}