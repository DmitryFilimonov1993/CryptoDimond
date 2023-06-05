package com.cryptodimond.presentation.ui

import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import kotlin.math.pow
import kotlin.math.roundToLong

fun Double.roundTo(signCountAfterCommon: Int = 1): Double {
    val tmp = 10.0.pow(signCountAfterCommon.toDouble())
    return (this * tmp).roundToLong() / tmp
}