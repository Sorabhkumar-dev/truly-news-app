package com.soarbh.trulynews.ui.screen.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.Article

class TopHeadlineSource constructor(
    private val repository: KtorClientRepository,
    private val topHeadlineRequest: TopHeadlineRequest
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = repository.getTopHeadlines(
                topHeadlineRequest.copy(
                    page = page,
                    pageSize = params.loadSize
                )
            )

            LoadResult.Page(
                data = if (response.articles.isNullOrEmpty()) emptyList() else response.articles!!,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isNullOrEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}