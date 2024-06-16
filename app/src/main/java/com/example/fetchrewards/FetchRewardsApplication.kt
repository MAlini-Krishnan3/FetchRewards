package com.example.fetchrewards

import android.app.Application
import com.example.fetchrewards.di.DaggerFetchRewardsAppComponent

class FetchRewardsApplication: Application() {
    val appComponent = DaggerFetchRewardsAppComponent.create()
}