package com.example.transactionstestapp

import android.app.Application

class TransactionsTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}