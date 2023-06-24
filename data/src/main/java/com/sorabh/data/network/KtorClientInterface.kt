package com.sorabh.data.network

import com.sorabh.data.pojo.request.AllNewsRequest
import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.NewsResponse

interface KtorClientInterface {
    suspend fun getTopHeadLines(topHeadlineRequest: TopHeadlineRequest): NewsResponse

    suspend fun getAllNewses(allNewsRequest: AllNewsRequest): NewsResponse
}