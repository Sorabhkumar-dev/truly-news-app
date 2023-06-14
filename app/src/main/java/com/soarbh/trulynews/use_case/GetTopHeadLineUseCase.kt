package com.soarbh.trulynews.use_case

import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.network.Result
import com.sorabh.data.pojo.TopHeadLineResponse
import kotlinx.coroutines.flow.Flow

class GetTopHeadLineUseCase constructor(private val repository: KtorClientRepository) :
    BaseUseCase<Unit, TopHeadLineResponse>() {
    override suspend fun getResult(params: Unit): Flow<Result<TopHeadLineResponse>> =
        repository.getTopHeadlines()
}