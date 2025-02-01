package org.example.project.utils

import kotlinx.coroutines.CoroutineDispatcher

expect fun provideDispatcher(): DispatcherProvider

interface DispatcherProvider {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}
