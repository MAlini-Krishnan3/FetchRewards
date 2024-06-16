package com.example.fetchrewards.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.fetchrewards.FetchRewardsApplication
import com.example.myapplication.R

class FetchRewardsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as FetchRewardsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, FetchRewardsFragment())
            }
        }
    }
}