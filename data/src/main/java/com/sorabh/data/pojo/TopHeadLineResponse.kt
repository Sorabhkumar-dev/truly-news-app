package com.sorabh.data.pojo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopHeadLineResponse(
    @SerialName("articles")
    val articles: List<Article?>?,
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?
)