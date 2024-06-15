package com.example.fetchrewards.di

import com.example.fetchrewards.MyApplication
import com.example.fetchrewards.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: MyApplication)
    fun inject(activity: MainActivity)
}