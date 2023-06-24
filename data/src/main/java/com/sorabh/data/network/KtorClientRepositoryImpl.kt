package com.sorabh.data.network

import com.sorabh.data.pojo.request.AllNewsRequest
import com.sorabh.data.pojo.request.SearchNewsRequest
import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.NewsResponse

class KtorClientRepositoryImpl constructor(private val ktorClientInterface: KtorClientInterface) :
    KtorClientRepository {
    override suspend fun getTopHeadlines(topHeadlineRequest: TopHeadlineRequest) =
        ktorClientInterface.getTopHeadLines(topHeadlineRequest)

    override suspend fun getAllNews(allNewsRequest: AllNewsRequest): NewsResponse =
        ktorClientInterface.getAllNewses(allNewsRequest)

    override suspend fun getSearchedNews(searchNewsRequest: SearchNewsRequest): NewsResponse =
        ktorClientInterface.getSearchedNewses(searchNewsRequest)
}