package com.soarbh.trulynews.ui.screen.filtered_news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.component.LottieAnim
import com.soarbh.trulynews.ui.component.NewsItemCard
import com.soarbh.trulynews.ui.component.OutlinedFilter
import com.soarbh.trulynews.ui.theme.spacing
import com.soarbh.trulynews.utils.enums.NewsFilterType
import kotlinx.coroutines.launch

@Composable
fun FilteredNewsScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: FilteredNewsViewModel
) {
    FilteredNewsScreenContent(
        paddingValues = paddingValues,
        navController = navController,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredNewsScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: FilteredNewsViewModel
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                Modifier
                    .height((LocalConfiguration.current.screenHeightDp - 165).dp)
                    .verticalScroll(rememberScrollState())
            ) {
                when (viewModel.selectedFilterType.collectAsStateWithLifecycle().value) {
                    NewsFilterType.Category -> CategoryFilters(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.space16),
                        viewModel = viewModel
                    )

                    NewsFilterType.Countries -> CountriesFilters(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.space16),
                        viewModel = viewModel
                    )

                    NewsFilterType.Source -> SourceFilter(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.space16),
                        viewModel = viewModel
                    )
                }
                Button(
                    onClick = {
                        viewModel.getTopHeadLines()
                        scope.launch {
                            scaffoldState.bottomSheetState.partialExpand()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.space16,
                            top = MaterialTheme.spacing.space16,
                            end = MaterialTheme.spacing.space16,
                            bottom = MaterialTheme.spacing.space48
                        )
                ) {
                    Text(
                        text = stringResource(id = R.string.get_filtered_news),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

        },
        modifier = Modifier.padding(paddingValues)
    ) { innerPadding ->
        val newsPagingDate = viewModel.filteredNewsFlow.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = MaterialTheme.spacing.space16),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
        ) {
            item {
                Filters(modifier = Modifier.fillMaxWidth()) {
                    viewModel.onFilterTypeChanged(it)
                    scope.launch {
                        if (scaffoldState.bottomSheetState.hasPartiallyExpandedState)
                            scaffoldState.bottomSheetState.expand()
                        else
                            scaffoldState.bottomSheetState.partialExpand()
                    }
                }
            }

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
                    if (newsPagingDate.itemSnapshotList.isEmpty())
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

}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun Filters(modifier: Modifier, onItemClicked: (NewsFilterType) -> Unit) {
    val filterItems = listOf(R.string.category, R.string.countries, R.string.source)

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
    ) {
        repeat(filterItems.size) { index ->
            OutlinedFilter(
                modifier = Modifier,
                filterItem = filterItems[index]
            ) {
                when (index) {
                    0 -> onItemClicked(NewsFilterType.Category)
                    1 -> onItemClicked(NewsFilterType.Countries)
                    else -> onItemClicked(NewsFilterType.Source)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategoryFilters(
    modifier: Modifier,
    viewModel: FilteredNewsViewModel
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
    ) {
        repeat(viewModel.categories.size) { index ->
            OutlinedFilter(
                modifier = Modifier,
                filterItem = viewModel.categories[index],
                isItemSelected = viewModel.categories[index] == viewModel.selectedCategory.collectAsStateWithLifecycle().value
            ) { category ->
                viewModel.onSelectedCategoryChanged(category)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CountriesFilters(modifier: Modifier, viewModel: FilteredNewsViewModel) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
    ) {
        repeat(viewModel.countriesMap.size) { index ->
            OutlinedFilter(
                modifier = Modifier,
                filterItem = viewModel.countriesMap.values.elementAt(index),
                isItemSelected = viewModel.countriesMap.keys.elementAt(index) == viewModel.selectedCountry.collectAsStateWithLifecycle().value
            ) {
                viewModel.onSelectedCountryChanged(viewModel.countriesMap.keys.elementAt(index))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SourceFilter(modifier: Modifier, viewModel: FilteredNewsViewModel) {
    FlowRow(modifier, horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)) {
        repeat(viewModel.sources.size) { index ->
            OutlinedFilter(
                modifier = Modifier,
                filterItem = viewModel.sources[index],
                isItemSelected = viewModel.selectedSources.contains(viewModel.sources[index])
            ) {
                viewModel.onSelectedSources(viewModel.sources[index])
            }
        }
    }
}