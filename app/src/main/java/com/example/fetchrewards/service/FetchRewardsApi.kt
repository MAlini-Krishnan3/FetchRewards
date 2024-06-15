package com.example.fetchrewards.service

import com.example.fetchrewards.constants.FetchRewardsConstants.FETCH_REWARDS_END_POINT
import com.example.fetchrewards.model.FetchRewardsModel
import io.reactivex.Single
import retrofit2.http.GET

interface FetchRewardsApi {

    @GET(FETCH_REWARDS_END_POINT)
    fun getRewardsData(): Single<List<FetchRewardsModel>>
}