package com.sorabh.data.network

import com.sorabh.data.pojo.TopHeadLineResponse
import kotlinx.coroutines.flow.Flow

interface KtorClientInterface {
    suspend fun getTopHeadLines(): Flow<Result<TopHeadLineResponse>>
}