package com.example.transactionstestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.Transaction
import com.example.transactionstestapp.common.*
import com.example.usecases.GetTransactionsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase
):  BaseViewModel(){

    private val _listState = MutableLiveData<Event<ListState>>()
    val listState: LiveData<Event<ListState>> = _listState
    private val _items = MutableLiveData<Event<List<Transaction>>>()
    val items: LiveData<Event<List<Transaction>>>
        get() {
            if(_items.value == null) getList()
            return _items
        }

    fun getList() {
        viewModelScope.launch {
            _listState.postValueEvent(ListState.Loading)
            delay(500)
            getTransactionsUseCase.invoke()
                .fold({
                    _listState.postValueEvent(ListState.Error)
                    sendFeedbackUser(FeedbackUser.from(it))
                },{
                    if(it.isNotEmpty()){
                        _listState.postValueEvent(ListState.Done)
                        _items.postValueEvent(it)
                    }else{
                        _listState.postValueEvent(ListState.Empty)
                    }
                })
        }
    }
}