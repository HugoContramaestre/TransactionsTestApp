package com.example.domain

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val BASIC_DATE_FORMAT = "dd/MM/yyyy HH:mm"

data class Transaction(
    val id: Int,
    val date: String,
    val amount: Float,
    val fee: Float,
    val description: String?
): ContentContract() {
    fun getDateObject(): Date?{
        return try {
            val df = SimpleDateFormat(DATE_FORMAT)
            df.parse(date)
        } catch (e: ParseException) {
            null
        }
    }

    fun getFormattedDate(): String {
        val formatter = SimpleDateFormat(BASIC_DATE_FORMAT)
        return formatter.format(getDateObject())
    }

    fun getDisplayAmount(): String {
        return "${amount + fee}"
    }

    override fun id() = id
}