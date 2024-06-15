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
    private val _items = MutableLiveData<Map<Int, List<FetchRewardsModel>>>()
    val itemsLiveData: LiveData<Map<Int, List<FetchRewardsModel>>> get() = _items

    fun fetchItems() {
        val disposable = dataRepository.fetchRewardItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { itemList ->
                itemList.filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy({ it.listId }, { it.name }))
                    .groupBy { it.listId }
            }
            .subscribe(
                { itemList -> _items.postValue(itemList) },
                { error -> _items.postValue(emptyMap()) } // Handle error
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}