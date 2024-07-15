package com.resy.photos.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Mapper<T, E> {
    fun map(input: T): E

    fun map(input: List<T>): List<E> = input.map { map(it) }
}

fun <R, E> Flow<Results<List<R>>>.mapWith(mapper: Mapper<R, E>): Flow<Results<List<E>>> =
    map {
        when (it) {
            is Results.Success -> Results.Success(mapper.map(it.data))
            is Results.Error -> Results.Error(it.throwable)
            is Results.Loading -> Results.Loading
        }
    }
