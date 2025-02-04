package org.example.project.urils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun provideDispatcher(): DispatcherProvider = AndroidDispatcher()

class AndroidDispatcher : DispatcherProvider {

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

}
