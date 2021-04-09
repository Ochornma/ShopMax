package com.shopmax.app.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispasherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}