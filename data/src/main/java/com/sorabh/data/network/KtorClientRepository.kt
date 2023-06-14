package com.sorabh.data.network

import com.sorabh.data.pojo.TopHeadLineResponse
import kotlinx.coroutines.flow.Flow

interface KtorClientRepository {

    suspend fun getTopHeadlines():Flow<Result<TopHeadLineResponse>>
}