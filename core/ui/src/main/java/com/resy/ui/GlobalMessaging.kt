package com.resy.ui

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

interface GlobalMessagingReceiver {
    val messagingFlow: SharedFlow<GlobalMessaging>
}

interface GlobalMessagingSender {
    fun send(globalMessaging: GlobalMessaging)
}

@Singleton
class GlobalMessagingImpl @Inject constructor(): GlobalMessagingReceiver ,GlobalMessagingSender {

    override fun send(globalMessaging: GlobalMessaging) {
        messagingFlow.tryEmit(globalMessaging)
    }

    override val messagingFlow: MutableSharedFlow<GlobalMessaging> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
}

sealed interface GlobalMessaging {
    data object NoInternet : GlobalMessaging
}
