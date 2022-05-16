package com.example.transactionstestapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.transactionstestapp.R
import com.google.android.material.snackbar.Snackbar


private const val DEFAULT_ANIMATION_DURATION = 1000L

fun View.show() {
    visibility = View.VISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.visible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.showSnackbarSimple(message: Int, isError: Boolean = false) {
    showSnackbarSimple(context.getString(message), isError)
}

fun View.showSnackbarSimple(message: String, isError: Boolean = false) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    with(snackbar.view) {
        setPadding(
            paddingLeft,
            context.resources.getDimensionPixelSize(R.dimen.snackbar_padding_top),
            paddingRight,
            paddingBottom
        )
        setBackgroundColor(
            ContextCompat.getColor(
                context,
                if (isError) {
                    R.color.error
                } else {
                    R.color.teal_700
                }
            )
        )
        findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
            setTextColor(ContextCompat.getColor(context, R.color.white))
            maxLines = 5
        }
        setOnClickListener {
            snackbar.dismiss()
        }
    }
    snackbar.show()
}

fun <T : ViewDataBinding> ViewGroup.bindingInflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = true
): T = DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, attachToRoot)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
