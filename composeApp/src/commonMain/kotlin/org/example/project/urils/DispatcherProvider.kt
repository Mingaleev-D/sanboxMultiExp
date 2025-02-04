package org.example.project.urils

import kotlinx.coroutines.CoroutineDispatcher

expect fun provideDispatcher(): DispatcherProvider

interface DispatcherProvider {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}
