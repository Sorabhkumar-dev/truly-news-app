package com.soarbh.trulynews.di

import com.soarbh.trulynews.ui.screen.home.HomeViewModel
import com.soarbh.trulynews.use_case.GetTopHeadLineUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory { GetTopHeadLineUseCase(get()) }
    viewModel{ HomeViewModel(get())}
}