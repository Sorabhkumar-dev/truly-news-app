package com.soarbh.trulynews.ui.screen.all_news

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
fun AllNewsScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: AllNewsViewModel
) {
    AllNewsScreenContent(
        paddingValues = paddingValues,
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun AllNewsScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: AllNewsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = MaterialTheme.spacing.space16)
    ) {
        Text(text = "All news screen", color = MaterialTheme.colorScheme.onBackground)
    }
}