package com.sorabh.data.network

import com.sorabh.data.pojo.request.AllNewsRequest
import com.sorabh.data.pojo.request.SearchNewsRequest
import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.NewsResponse

interface KtorClientRepository {

    suspend fun getTopHeadlines(topHeadlineRequest: TopHeadlineRequest):NewsResponse

    suspend fun getAllNews(allNewsRequest: AllNewsRequest):NewsResponse

    suspend fun getSearchedNews(searchNewsRequest: SearchNewsRequest):NewsResponse
}