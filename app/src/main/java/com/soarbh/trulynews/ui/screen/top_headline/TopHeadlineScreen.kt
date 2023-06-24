package com.soarbh.trulynews.ui.screen.top_headline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.component.LottieAnim
import com.soarbh.trulynews.ui.component.NewsItemCard
import com.soarbh.trulynews.ui.theme.spacing

@Composable
fun TopHeadlineScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: TopHeadlineViewModel
) {
    TopHeadlineScreenContent(
        paddingValues = paddingValues,
        viewModel = viewModel
    )
}

@Composable
fun TopHeadlineScreenContent(
    paddingValues: PaddingValues,
    viewModel: TopHeadlineViewModel
) {
    val newsPagingDate = viewModel.getTopHeadLines().flow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = MaterialTheme.spacing.space16),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
    ) {


        when (newsPagingDate.loadState.refresh) {
            LoadState.Loading -> item {
                LottieAnim(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.spacing.space150),
                    lottieFile = R.raw.loading_animination
                )
            }

            is LoadState.Error -> item {
                LottieAnim(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.spacing.space300),
                    lottieFile = R.raw.error_animination
                )
            }

            is LoadState.NotLoading ->
                if (newsPagingDate.itemSnapshotList.isEmpty() && newsPagingDate.loadState.refresh.endOfPaginationReached)
                    item {
                        LottieAnim(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(MaterialTheme.spacing.space150),
                            lottieFile = R.raw.empty_animination
                        )
                    }
                else
                    items(
                        count = newsPagingDate.itemCount,
                        key = newsPagingDate.itemKey(),
                        contentType = newsPagingDate.itemContentType(
                        )
                    ) { index ->
                        NewsItemCard(
                            modifier = Modifier.fillMaxWidth(),
                            article = newsPagingDate[index]!!
                        )
                    }
        }


        when (newsPagingDate.loadState.append) {
            LoadState.Loading -> item {
                LottieAnim(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.spacing.space150),
                    lottieFile = R.raw.loading_animination
                )
            }

            is LoadState.Error -> item {
                LottieAnim(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.spacing.space150),
                    lottieFile = R.raw.error_animination
                )
            }

            is LoadState.NotLoading ->
                if (newsPagingDate.itemSnapshotList.isEmpty() && newsPagingDate.loadState.refresh.endOfPaginationReached)
                    item {
                        LottieAnim(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(MaterialTheme.spacing.space150),
                            lottieFile = R.raw.empty_animination
                        )
                    }
                else
                    items(
                        count = newsPagingDate.itemCount,
                        key = newsPagingDate.itemKey(),
                        contentType = newsPagingDate.itemContentType(
                        )
                    ) { index ->
                        NewsItemCard(
                            modifier = Modifier.fillMaxWidth(),
                            article = newsPagingDate[index]!!
                        )
                    }
        }

    }
}

