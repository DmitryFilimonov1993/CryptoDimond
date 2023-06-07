package com.cryptodimond.presentation.ui

import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

const val INPUT_DATA_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DD_MM_YYY_DATA_PATTERN = "dd.MM.yyyy"

fun String.parseToDateString(
    inputPattern: String = INPUT_DATA_PATTERN,
    outputPattern: String = DD_MM_YYY_DATA_PATTERN,
    local: Locale = Locale.ENGLISH
): String {
    val inputFormatter = DateTimeFormatter.ofPattern(inputPattern, local)
    val outputFormatter = DateTimeFormatter.ofPattern(outputPattern, local)
    return try {
        val date = LocalDateTime.parse(this, inputFormatter)
        outputFormatter.format(date)
    } catch (ex: Exception) {
        Log.d("DATE_TIME_PARSING", "error: ${ex.message}")
        "-1"
    }
}