package com.example.data.exception

sealed class Failure: Exception() {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object UnknownApiError : Failure()
    object UnexpectedFailure : Failure()
    object Outdated : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()

    object Conflict : FeatureFailure()

//    class ApiError(val code: String, val message: String) : FeatureFailure()

    companion object {
        fun api(code: Int?): Failure {
            return when (code) {
                in 400..409 -> Conflict
                else -> UnknownApiError
            }
        }
    }
}
