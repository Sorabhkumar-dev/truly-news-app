package com.sorabh.data.network

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend inline fun <reified T> handleApiResponse(crossinline getResult: suspend () -> HttpResponse): Flow<Result<T>> =
    flow {
        emit(Result.Loading())
        emit(
            try {
                val response = getResult()
                when (response.status.value) {
                    // 2xx - responses -> Success
                    in 200..299 ->
                        Result.Success(
                            response.body(),
                            response.status.value,
                            response.status.description
                        )
                    in 300..399 -> throw RedirectResponseException(
                        response,
                        response.status.description
                    )
                    in 400..499 -> throw ClientRequestException(
                        response,
                        response.status.description
                    )
                    in 500..599 -> throw ServerResponseException(
                        response,
                        response.status.description
                    )
                    else -> throw Exception()
                }
            } catch (e: RedirectResponseException) {
                // 3xx - responses
                Result.Error(e.response.status.value, e.response.status.description)
            } catch (e: ClientRequestException) {
                // 4xx - responses
                Result.Error(e.response.status.value, e.response.status.description)
            } catch (e: ServerResponseException) {
                // 5xx - responses
                Result.Error(e.response.status.value, e.response.status.description)
            } catch (e: Exception) {
                Result.Error(500, e.message.toString())
            }
        )
    }