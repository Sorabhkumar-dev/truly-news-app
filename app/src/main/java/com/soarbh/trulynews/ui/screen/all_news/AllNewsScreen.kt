package com.soarbh.trulynews.ui.screen.all_news

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun  AllNewsScreen(navController: NavController,viewModel: AllNewsViewModel) {
    AllNewsScreenContent(navController = navController, viewModel = viewModel)
}

@Composable
fun  AllNewsScreenContent(navController: NavController,viewModel: AllNewsViewModel) {
    Text(text = "All news screen")
}