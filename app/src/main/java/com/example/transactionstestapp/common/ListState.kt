package com.example.transactionstestapp.common

sealed class ListState {
    object Loading: ListState()
    object Empty: ListState()
    object Error: ListState()
    object Done: ListState()
}
