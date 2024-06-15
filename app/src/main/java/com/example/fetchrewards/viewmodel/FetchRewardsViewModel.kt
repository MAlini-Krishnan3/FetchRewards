package com.example.fetchrewards.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchrewards.datarepo.FetchRewardsDataRepo
import com.example.fetchrewards.model.FetchRewardsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchRewardsViewModel @Inject constructor(private val dataRepository: FetchRewardsDataRepo) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _items = MutableLiveData<List<FetchRewardsModel>>()
    val itemsLiveData: LiveData<List<FetchRewardsModel>> get() = _items

    fun fetchItems() {
        val disposable = dataRepository.fetchRewardItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { itemList -> _items.postValue(itemList) },
                { error -> _items.postValue(emptyList()) } // Handle error
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}