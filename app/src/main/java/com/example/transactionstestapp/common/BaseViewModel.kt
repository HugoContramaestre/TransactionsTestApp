package com.example.transactionstestapp.common

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel(){
    private val _feedbackUser = MutableLiveData<Event<FeedbackUser>>()
    internal val feedbackUser: LiveData<Event<FeedbackUser>> get() = _feedbackUser

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

    fun sendFeedbackUser(feedbackUser: FeedbackUser) = _feedbackUser.postValueEvent(feedbackUser)
}