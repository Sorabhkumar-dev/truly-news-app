package com.soarbh.trulynews.ui.screen.search_news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.component.LottieAnim
import com.soarbh.trulynews.ui.component.SearchNewsItemCard
import com.soarbh.trulynews.ui.theme.spacing

@Composable
fun SearchNewsScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: SearchNewsViewModel
) {
    SearchScreenContent(
        paddingValues = paddingValues,
        navController = navController,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: SearchNewsViewModel
) {
    val searchNewsPagingData = viewModel.debounceSearch().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = MaterialTheme.spacing.space16),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
    ) {
        item {
            OutlinedTextField(
                value = viewModel.searchToken.collectAsStateWithLifecycle().value,
                onValueChange = viewModel::onSearchTokenChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.space16),
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.search_your_newses),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.write_your_news_query_above),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search news Icon"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = viewModel::onSearchClearBtnClicked) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close search icon"
                        )
                    }
                },
                isError = !viewModel.isValidSearchQuery()
            )
        }


        when (searchNewsPagingData.loadState.refresh) {
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
                if (searchNewsPagingData.itemSnapshotList.isEmpty() && searchNewsPagingData.loadState.refresh.endOfPaginationReached)
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
                        count = searchNewsPagingData.itemCount,
                        key = searchNewsPagingData.itemKey(),
                        contentType = searchNewsPagingData.itemContentType(
                        )
                    ) { index ->
                        SearchNewsItemCard(
                            modifier = Modifier.fillMaxWidth(),
                            article = searchNewsPagingData[index]!!
                        )
                    }
        }


        when (searchNewsPagingData.loadState.append) {
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
                if (searchNewsPagingData.itemSnapshotList.isEmpty() && searchNewsPagingData.loadState.refresh.endOfPaginationReached)
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
                        count = searchNewsPagingData.itemCount,
                        key = searchNewsPagingData.itemKey(),
                        contentType = searchNewsPagingData.itemContentType(
                        )
                    ) { index ->
                        SearchNewsItemCard(
                            modifier = Modifier.fillMaxWidth(),
                            article = searchNewsPagingData[index]!!
                        )
                    }
        }

    }
}