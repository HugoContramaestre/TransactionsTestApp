package com.example.transactionstestapp.api

import com.example.data.exception.Failure
import com.example.transactionstestapp.api.ErrorResult

open class FailureFactory {

    open fun handleCode(code: Int? = -1) =
        /* Si se tuviesen que controlar errores especificos en base a un codigo de error,
        como el token invalido, se pondria un when con las posibilidades
         */
        Failure.api(code)

    open fun handleException(e: Exception? = null) = Failure.UnexpectedFailure

    open fun handleApiCode(code: Int?, errorResult: ErrorResult?) =
        if (errorResult == null) {
            handleCode(code)
        } else {
            // aqui controlariamos el manejo del codigo de error en base al objeto de error
            handleCode(code)
        }
}