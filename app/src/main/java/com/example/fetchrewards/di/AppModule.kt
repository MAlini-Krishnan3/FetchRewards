package com.example.fetchrewards.di

import androidx.lifecycle.ViewModelProvider
import com.example.fetchrewards.datarepo.FetchRewardsDataRepo
import com.example.fetchrewards.service.FetchRewardsApi
import com.example.myapplication.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideFetchRewardsApi(retrofit: Retrofit): FetchRewardsApi {
            return retrofit.create(FetchRewardsApi::class.java)
        }

        @Provides
        @Singleton
        fun provideFetchRewardsDataRepo(apiService: FetchRewardsApi): FetchRewardsDataRepo {
            return FetchRewardsDataRepo(apiService)
        }
    }

}