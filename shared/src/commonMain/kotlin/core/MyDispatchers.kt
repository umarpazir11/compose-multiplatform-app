package core

import kotlinx.coroutines.CoroutineDispatcher

interface MyDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

expect val myDispatchers: MyDispatchers