package com.soarbh.trulynews.ui.screen.top_headline

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Tune
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.screen.navigation.ScreenNavigator
import com.soarbh.trulynews.use_case.GetTopHeadLineUseCase
import com.sorabh.data.network.Result
import com.sorabh.data.pojo.TopHeadLineResponse
import com.sorabh.data.pojo.other.DrawerItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopHeadlineViewModel constructor(private val topHeadLineUseCase: GetTopHeadLineUseCase) :
    ViewModel() {
    private val _topHeadLinesFlow: MutableStateFlow<Result<TopHeadLineResponse>> =
        MutableStateFlow(Result.Loading())
    val topHeadLinesFlow = _topHeadLinesFlow.asStateFlow()

    init {
        getTopHeadLines()
    }

    private fun getTopHeadLines() {
        viewModelScope.launch {
            topHeadLineUseCase(Unit).collect { _topHeadLinesFlow.value = it }
        }
    }
}