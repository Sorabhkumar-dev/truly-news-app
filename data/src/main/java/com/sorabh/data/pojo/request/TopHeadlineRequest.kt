package com.sorabh.data.pojo.request

data class TopHeadlineRequest(
    val page:Int,
    val pageSize:Int = 10,
    val apiKey: String,
    val country: String?,
    val category: String?,
    val sources: String?,
)