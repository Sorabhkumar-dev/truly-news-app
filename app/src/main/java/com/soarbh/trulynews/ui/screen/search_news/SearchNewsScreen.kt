package com.soarbh.trulynews.ui.screen.search_news

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SearchNewsScreen(navController: NavController, viewModel: SearchNewsViewModel) {
    SearchScreenContent(navController = navController, viewModel = viewModel)
}

@Composable
fun SearchScreenContent(navController: NavController,viewModel: SearchNewsViewModel){
Text(text = "Search news here")
}