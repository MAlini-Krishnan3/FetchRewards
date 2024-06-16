package com.example.fetchrewards.di

import com.example.fetchrewards.FetchRewardsApplication
import com.example.fetchrewards.views.FetchRewardsFragment
import com.example.fetchrewards.views.FetchRewardsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FetchRewardsModule::class])
interface FetchRewardsAppComponent {
    fun inject(app: FetchRewardsApplication)
    fun inject(activity: FetchRewardsActivity)
    fun inject(fragment: FetchRewardsFragment)
}