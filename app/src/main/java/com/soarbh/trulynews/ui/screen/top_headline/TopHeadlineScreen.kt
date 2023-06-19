package com.soarbh.trulynews.ui.screen.top_headline

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TopHeadlineScreen(navController: NavController, viewModel: TopHeadlineViewModel) {
    TopHeadlineScreenContent(navController = navController, viewModel = viewModel)
}

@Composable
fun TopHeadlineScreenContent(navController: NavController,viewModel: TopHeadlineViewModel){
    Text(text = "TopHeadline news here")
}
