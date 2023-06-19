package com.soarbh.trulynews.di

import com.soarbh.trulynews.ui.screen.all_news.AllNewsViewModel
import com.soarbh.trulynews.ui.screen.filtered_news.FilteredNewsViewModel
import com.soarbh.trulynews.ui.screen.home_screen.HomeViewModel
import com.soarbh.trulynews.ui.screen.search_news.SearchNewsViewModel
import com.soarbh.trulynews.ui.screen.top_headline.TopHeadlineViewModel
import com.soarbh.trulynews.use_case.GetTopHeadLineUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory { GetTopHeadLineUseCase(get()) }
    viewModel{ HomeViewModel() }
    viewModel{ TopHeadlineViewModel(get())}
    viewModel{ AllNewsViewModel() }
    viewModel{ FilteredNewsViewModel() }
    viewModel { SearchNewsViewModel() }
}