package com.sorabh.data.network

import com.sorabh.data.pojo.request.AllNewsRequest
import com.sorabh.data.pojo.request.SearchNewsRequest
import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.NewsResponse
import com.sorabh.data.util.ApiRoutes
import com.sorabh.data.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorClientInterfaceImpl constructor(private val httpClient: HttpClient) :
    KtorClientInterface {
    override suspend fun getTopHeadLines(topHeadlineRequest: TopHeadlineRequest): NewsResponse =
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

    override suspend fun getAllNewses(allNewsRequest: AllNewsRequest): NewsResponse =
        httpClient.get(Constant.BASE_URL.plus(ApiRoutes.ALL_NEWS_REQUEST)) {
            parameter("page",allNewsRequest.topHeadlineRequest.page)
            parameter("pageSize",allNewsRequest.topHeadlineRequest.pageSize)
            parameter("apiKey", allNewsRequest.topHeadlineRequest.apiKey)
            parameter("q",allNewsRequest.searchToken)
            allNewsRequest.topHeadlineRequest.category?.let { category ->
                parameter("category", category)
            }
            allNewsRequest.topHeadlineRequest.sources?.let{ sources ->
                parameter("sources",sources)
            }
        }.body()

    override suspend fun getSearchedNewses(searchNewsRequest: SearchNewsRequest): NewsResponse =
        httpClient.get(Constant.BASE_URL.plus(ApiRoutes.ALL_NEWS_REQUEST)) {
            parameter("page",searchNewsRequest.topHeadlineRequest.page)
            parameter("pageSize",searchNewsRequest.topHeadlineRequest.pageSize)
            parameter("apiKey", searchNewsRequest.topHeadlineRequest.apiKey)
            parameter("q",searchNewsRequest.searchToken)
            searchNewsRequest.topHeadlineRequest.category?.let { category ->
                parameter("category", category)
            }
            searchNewsRequest.topHeadlineRequest.sources?.let{ sources ->
                parameter("sources",sources)
            }
        }.body()

}
