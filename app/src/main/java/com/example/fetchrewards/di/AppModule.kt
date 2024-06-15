package com.example.fetchrewards.di

import com.example.fetchrewards.service.FetchRewardsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFetchRewardsApi(retrofit: Retrofit): FetchRewardsApi {
        return retrofit.create(FetchRewardsApi::class.java)
    }
}