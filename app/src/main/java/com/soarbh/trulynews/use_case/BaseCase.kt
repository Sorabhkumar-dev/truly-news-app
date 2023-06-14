package com.soarbh.trulynews.use_case

import kotlinx.coroutines.flow.Flow
import com.sorabh.data.network.Result

abstract class BaseUseCase<P, R> {
    private suspend fun getResponse(params: P): Flow<Result<R>> = getResult(params)
    abstract suspend fun getResult(params: P): Flow<Result<R>>
    suspend operator fun invoke(params: P) = getResponse(params)
}