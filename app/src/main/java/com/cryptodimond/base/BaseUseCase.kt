package com.cryptodimond.base

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}