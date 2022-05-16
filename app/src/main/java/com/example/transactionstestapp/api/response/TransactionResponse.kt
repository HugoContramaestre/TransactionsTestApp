package com.example.transactionstestapp.api.response

import android.annotation.SuppressLint
import com.example.domain.Transaction
import com.example.transactionstestapp.DATE_FORMAT
import java.text.ParseException
import java.text.SimpleDateFormat

data class TransactionResponse(
    val id: Int?,
    val date: String?,
    val amount: Float?,
    val fee: Float?,
    val description: String?
) {
    fun toDomain() = Transaction(
        id = id ?: -1,
        date = if(isDateValid()) date!! else "",
        amount = amount ?: 0F,
        fee = fee ?: 0F,
        description = description
    )

    @SuppressLint("SimpleDateFormat")
    private fun isDateValid(): Boolean {
        return try {
            val df = SimpleDateFormat(DATE_FORMAT)
            df.isLenient = false
            df.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }
}
