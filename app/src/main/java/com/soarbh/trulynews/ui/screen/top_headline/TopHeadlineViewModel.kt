package com.soarbh.trulynews.ui.screen.top_headline

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.soarbh.trulynews.R
import com.soarbh.trulynews.TrulyApp
import com.soarbh.trulynews.ui.screen.paging_source.TopHeadlineSource
import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.pojo.request.TopHeadlineRequest

class TopHeadlineViewModel constructor(private val repository: KtorClientRepository) :
    ViewModel() {

    init {
        getTopHeadLines()
    }

    fun getTopHeadLines() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
        TopHeadlineSource(
            repository = repository,
            topHeadlineRequest = TopHeadlineRequest(
                10,10,TrulyApp.R().getString(R.string.news_api_key),"in",null,null
            )
        )}
    )
}