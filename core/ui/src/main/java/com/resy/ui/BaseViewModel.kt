package com.resy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    @Inject
    lateinit var globalMessagingSender: GlobalMessagingSender

    override val coroutineContext: CoroutineContext =
        viewModelScope.coroutineContext + CoroutineExceptionHandler { _, throwable ->
            processError(throwable)
        }

    protected fun processError(throwable: Throwable) {
        when (throwable) {
            is CancellationException -> return
            is UnknownHostException -> globalMessagingSender.send(GlobalMessaging.NoInternet)
            else -> onError(throwable)
        }
    }

    open fun onError(throwable: Throwable) {
        Timber.e(throwable)
    }
}