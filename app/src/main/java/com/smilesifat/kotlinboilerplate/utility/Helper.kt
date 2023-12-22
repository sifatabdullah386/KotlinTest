package com.smilesifat.kotlinboilerplate.utility

import android.annotation.SuppressLint
import androidx.core.net.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Suppress("NAME_SHADOWING")
open class Helper {
    companion object {
        fun DateFormat(selectedDate: String?): String? {
            var selectedDate = selectedDate
            val inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val outputPattern = "d, MMM yy HH:mm a"
            @SuppressLint("SimpleDateFormat") val inputFormat = SimpleDateFormat(inputPattern)
            @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat(outputPattern)
            var date: Date? = null
            try {
                date = inputFormat.parse(selectedDate!!)
                selectedDate = outputFormat.format(Objects.requireNonNull(date))
            } catch (e: ParseException) {
                e.printStackTrace()
            } catch (e: java.text.ParseException) {
                e.printStackTrace()
            }
            return selectedDate
        }
    }

}