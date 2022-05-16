package com.example.usecases

import com.example.data.repository.MainRepository

class GetTransactionsUseCase(private val repository: MainRepository) {
    suspend fun invoke() = repository.getTransactions()
}