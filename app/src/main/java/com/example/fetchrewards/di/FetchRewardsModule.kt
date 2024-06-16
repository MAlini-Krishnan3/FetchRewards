package com.example.fetchrewards.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchrewards.constants.FetchRewardsConstants.BASE_URL
import com.example.fetchrewards.datarepo.FetchRewardsDataRepo
import com.example.fetchrewards.service.FetchRewardsApi
import com.example.fetchrewards.viewmodel.FetchRewardsViewModel
import com.example.myapplication.di.ViewModelFactory
import com.example.myapplication.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
abstract class FetchRewardsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FetchRewardsViewModel::class)
    abstract fun bindMainViewModel(viewModel: FetchRewardsViewModel): ViewModel

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
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