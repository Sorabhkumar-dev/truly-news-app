package com.sorabh.data.network

import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.TopHeadLineResponse

interface KtorClientRepository {

    suspend fun getTopHeadlines(topHeadlineRequest: TopHeadlineRequest):TopHeadLineResponse
}