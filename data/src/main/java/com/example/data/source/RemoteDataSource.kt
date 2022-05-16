package com.example.data.source

import com.example.data.exception.Failure
import com.example.data.functional.Either
import com.example.domain.Transaction

interface RemoteDataSource {

    suspend fun getTransactions(): Either<Failure, List<Transaction>>

}