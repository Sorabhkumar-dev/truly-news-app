package com.soarbh.trulynews.ui.screen.all_news

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.soarbh.trulynews.R
import com.soarbh.trulynews.TrulyApp
import com.soarbh.trulynews.ui.screen.paging_source.AllNewsSource
import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.pojo.request.AllNewsRequest
import com.sorabh.data.pojo.request.TopHeadlineRequest

class AllNewsViewModel constructor(private val repository: KtorClientRepository) : ViewModel() {

    fun getAllNews() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            AllNewsSource(
                repository = repository,
                allNewsRequest = AllNewsRequest(
                    "india",
                    TopHeadlineRequest(
                        10, 10,
                        TrulyApp.R().getString(R.string.news_api_key),
                        "in",
                        null,
                        null
                    )
                )
            )
        })
}