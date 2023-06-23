package com.sorabh.data.network

import com.sorabh.data.pojo.response.TopHeadLineResponse
import kotlinx.coroutines.flow.Flow

interface KtorClientRepository {

    suspend fun getTopHeadlines():Flow<Result<TopHeadLineResponse>>
}