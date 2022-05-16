package com.example.transactionstestapp.api

import com.example.transactionstestapp.api.response.TransactionResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("transactions.json")
    suspend fun getTransactions(): NetworkResponse<List<TransactionResponse>, ErrorResult>
}