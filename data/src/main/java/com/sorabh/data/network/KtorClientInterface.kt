package com.sorabh.data.network

import com.sorabh.data.pojo.response.TopHeadLineResponse
import kotlinx.coroutines.flow.Flow

interface KtorClientInterface {
    suspend fun getTopHeadLines(): Flow<Result<TopHeadLineResponse>>
}