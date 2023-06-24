package com.sorabh.data.network

import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.TopHeadLineResponse

interface KtorClientInterface {
    suspend fun getTopHeadLines(topHeadlineRequest: TopHeadlineRequest): TopHeadLineResponse
}