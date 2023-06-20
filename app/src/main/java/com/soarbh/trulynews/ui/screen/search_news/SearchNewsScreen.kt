package com.soarbh.trulynews.ui.screen.search_news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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

@Composable
fun SearchScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: SearchNewsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = MaterialTheme.spacing.space16)
    ) {
        Text(text = "Search news here", color = MaterialTheme.colorScheme.onBackground)
    }
}