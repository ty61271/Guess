package com.west.pratice.guess

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

fun Int.validate(number: Int): Int {
    return this - number
}

fun View.setBaseAlerDialog(
    context: Context,
    title: String? = null,
    message: String? = null
): AlertDialog.Builder {
    return AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
    }
}


