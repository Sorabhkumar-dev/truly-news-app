package com.soarbh.trulynews.ui.screen.filtered_news

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun FilteredNewsScreen(navController: NavController,viewModel: FilteredNewsViewModel) {
    FilteredNewsScreenContent(navController = navController, viewModel = viewModel)
}

@Composable
fun FilteredNewsScreenContent(navController: NavController,viewModel: FilteredNewsViewModel){
Text(text = "filtered news")
}