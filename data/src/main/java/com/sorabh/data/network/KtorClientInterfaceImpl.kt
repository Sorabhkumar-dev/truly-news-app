package com.sorabh.data.network

import com.sorabh.data.pojo.TopHeadLineResponse
import com.sorabh.data.util.ApiRoutes
import com.sorabh.data.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow

class KtorClientInterfaceImpl constructor(private val httpClient: HttpClient) :
    KtorClientInterface {
    override suspend fun getTopHeadLines(): Flow<Result<TopHeadLineResponse>> = handleApiResponse {
        httpClient.get(Constant.BASE_URL.plus(ApiRoutes.TOP_HEADLINES_REQUEST)){
            parameter("apiKey","f5cd498bf1ed4abaa3acd25694c95808")
            parameter("country","in")
            parameter("category","business")
        }
    }
}