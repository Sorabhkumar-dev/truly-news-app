package com.soarbh.trulynews.ui.screen.search_news

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.soarbh.trulynews.R
import com.soarbh.trulynews.TrulyApp
import com.soarbh.trulynews.ui.screen.paging_source.SearchedNewsSource
import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.pojo.request.SearchNewsRequest
import com.sorabh.data.pojo.request.TopHeadlineRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

class SearchNewsViewModel constructor(private val repository: KtorClientRepository) : ViewModel() {

    val searchToken = MutableStateFlow("")

    fun onSearchTokenChanged(search: String) {
        searchToken.value = search
    }

    fun onSearchClearBtnClicked() {
        searchToken.value = ""
    }

    fun isValidSearchQuery() = searchToken.value.isNotEmpty() && searchToken.value.length > 2

    fun debounceSearch() =
        searchToken.debounce(300).filter { isValidSearchQuery() }.distinctUntilChanged()
            .flatMapLatest { query -> getSearchedNewses(query).flow }.flowOn(Dispatchers.Default)


    private fun getSearchedNewses(searchToken: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchedNewsSource(
                repository = repository,
                searchNewsRequest = SearchNewsRequest(
                    searchToken,
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