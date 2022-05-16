package com.example.data.repository

import com.example.data.source.RemoteDataSource

class MainRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getTransactions() = remoteDataSource.getTransactions()
}