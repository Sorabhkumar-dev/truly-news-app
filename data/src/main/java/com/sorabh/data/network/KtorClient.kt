package com.sorabh.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.statement.request
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.BuildConfig

class KtorClient {
    companion object {
        val ktorHttpClient = HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 20_000
                requestTimeoutMillis = 20_000
                socketTimeoutMillis = 20_000
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor => $message")
                    }
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    if (BuildConfig.DEBUG) {
                        println("Ktor Status => ${response.status}")
                        println("Ktor Request => ${response.request}")
                        println("Ktor Call => ${response.call}")
                        println("Ktor Header => ${response.headers}")
                        println("Ktor Request_Method_Type => ${response.request.method}")
                    }
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, Json)
                accept(Json)
//                url {
//                    protocol = URLProtocol.HTTPS
//                    host = "https://newsapi.org/v2/"
//                    parameters.append("apiKey", "f5cd498bf1ed4abaa3acd25694c95808")
//                }
            }
        }
    }
}