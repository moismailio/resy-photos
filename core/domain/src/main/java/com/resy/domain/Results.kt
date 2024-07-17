package com.resy.domain

sealed interface Results<out T> {
    data class Success<T>(
        val data: T,
    ) : Results<T>

    data class Error<T>(
        val throwable: Throwable,
    ) : Results<T>

    data object Loading : Results<Nothing>
}
