package com.example.fetchrewards

import android.app.Application
import com.example.fetchrewards.di.DaggerAppComponent

class MyApplication: Application() {
    val appComponent = DaggerAppComponent.create()
}