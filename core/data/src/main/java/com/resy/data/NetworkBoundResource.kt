package com.resy.data

import com.resy.domain.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class NetworkBoundResource
    @Inject
    constructor() {
        suspend fun <T> fetch(api: suspend () -> Response<T>): Flow<Results<T>> =
            withContext(Dispatchers.IO) {
                flow {
                    emit(Results.Loading)
                    val response = api()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(Results.Success(data = it))
                        } ?: emit(Results.Error(UnknownError()))
                    } else {
                        emit(Results.Error(Throwable(response.message())))
                    }
                }.catch { error ->
                    Timber.e(error.message)
                    emit(Results.Error(error))
                }
            }
    }
