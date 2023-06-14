package com.sorabh.data.network

class KtorClientRepositoryImpl constructor(private val ktorClientInterface: KtorClientInterface) :
    KtorClientRepository {
    override suspend fun getTopHeadlines() = ktorClientInterface.getTopHeadLines()

}