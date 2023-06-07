package com.cryptodimond.presentation.ui

import kotlin.math.pow
import kotlin.math.roundToLong

const val MILLION = 1000000L
const val BILLION = 1000000000L
const val TRILLION = 1000000000000L

fun Double.roundTo(signCountAfterCommon: Int = 1): Double {
    val tmp = 10.0.pow(signCountAfterCommon)
    return (this * tmp).roundToLong() / tmp
}

fun Double.abbreviate(): String {
   return when {
        this < MILLION -> this.roundTo(2).toString()
        this < BILLION -> (this/ MILLION).roundTo(2).toString() + " Mn"
        this < TRILLION -> (this/ BILLION).roundTo(2).toString() + " Bn"
        else  -> (this/ TRILLION).roundTo(2).toString() + " Tn"
    }
}