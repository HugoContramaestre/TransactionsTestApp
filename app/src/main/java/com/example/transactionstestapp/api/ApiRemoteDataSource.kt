package com.example.transactionstestapp.api

import com.example.data.exception.Failure
import com.example.data.functional.Either
import com.example.data.source.RemoteDataSource
import com.example.domain.Transaction
import com.haroldadmin.cnradapter.NetworkResponse

class ApiRemoteDataSource(private val api: ApiServices): RemoteDataSource {
    override suspend fun getTransactions(): Either<Failure, List<Transaction>> {
        return api.getTransactions().wrapperResponse {
            it.map { item -> item.toDomain() }
                .filter { item-> item.id != -1 && item.getDateObject() != null }
                .sortedByDescending { item -> item.date }
        }
    }
}

inline fun <In : Any, Out : Any> NetworkResponse<In, ErrorResult>.wrapperResponse(
    transform: ((In) -> Out)
): Either<Failure, Out> {
    return when (this) {
        is NetworkResponse.Success -> Either.Right(transform.invoke(body))
        is NetworkResponse.ServerError -> Either.Left(
            FailureFactory().handleApiCode(
                code,
                body
            )
        )
        else -> Either.Left(Failure.NetworkConnection)
    }
}

fun <In : Any> NetworkResponse<In, ErrorResult>.wrapperResponseEmpty(): Failure? {
    return when (this) {
        is NetworkResponse.Success -> null
        is NetworkResponse.ServerError ->
            FailureFactory().handleApiCode(
                code,
                body
            )
        else -> Failure.NetworkConnection
    }
}