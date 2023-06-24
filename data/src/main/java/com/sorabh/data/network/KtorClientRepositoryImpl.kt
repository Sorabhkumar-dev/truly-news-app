package com.sorabh.data.network

import com.sorabh.data.pojo.request.TopHeadlineRequest

class KtorClientRepositoryImpl constructor(private val ktorClientInterface: KtorClientInterface) :
    KtorClientRepository {
    override suspend fun getTopHeadlines(topHeadlineRequest: TopHeadlineRequest) =
        ktorClientInterface.getTopHeadLines(topHeadlineRequest)

}