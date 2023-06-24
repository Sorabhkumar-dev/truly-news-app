package com.sorabh.data.network

import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.TopHeadLineResponse
import com.sorabh.data.util.ApiRoutes
import com.sorabh.data.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorClientInterfaceImpl constructor(private val httpClient: HttpClient) :
    KtorClientInterface {
    override suspend fun getTopHeadLines(topHeadlineRequest: TopHeadlineRequest): TopHeadLineResponse =
        httpClient.get(Constant.BASE_URL.plus(ApiRoutes.TOP_HEADLINES_REQUEST)) {
            parameter("page",topHeadlineRequest.page)
            parameter("pageSize",topHeadlineRequest.pageSize)
            parameter("apiKey", topHeadlineRequest.apiKey)

            topHeadlineRequest.country?.let { country ->
                parameter("country", country)
            }
            topHeadlineRequest.category?.let { category ->
                parameter("category", category)
            }
            topHeadlineRequest.sources?.let{ sources ->
                parameter("sources",sources)
            }
        }.body()

}
