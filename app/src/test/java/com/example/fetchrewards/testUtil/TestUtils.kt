package com.example.fetchrewards.testUtil

import com.example.fetchrewards.model.FetchRewardsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import java.io.InputStreamReader

object TestUtils {
    fun getMockItemsFromJson(fileName: String): List<FetchRewardsModel> {
        val inputStream = this::class.java.classLoader?.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("File not found: $fileName")
        val reader = InputStreamReader(inputStream)
        val itemType = object : TypeToken<List<FetchRewardsModel>>() {}.type
        return Gson().fromJson(reader, itemType)
    }

    fun setUpRxSchedulers() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    fun tearDownRxSchedulers() {
        RxAndroidPlugins.reset()
    }
}