package com.soarbh.trulynews.ui.screen.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.soarbh.trulynews.ui.screen.all_news.AllNewsScreen
import com.soarbh.trulynews.ui.screen.all_news.AllNewsViewModel
import com.soarbh.trulynews.ui.screen.filtered_news.FilteredNewsScreen
import com.soarbh.trulynews.ui.screen.filtered_news.FilteredNewsViewModel
import com.soarbh.trulynews.ui.screen.search_news.SearchNewsScreen
import com.soarbh.trulynews.ui.screen.search_news.SearchNewsViewModel
import com.soarbh.trulynews.ui.screen.top_headline.TopHeadlineScreen
import com.soarbh.trulynews.ui.screen.top_headline.TopHeadlineViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(paddingValues: PaddingValues, navHostController: NavHostController) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = ScreenNavigator.TopHeadlineScreen.name,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right, animationSpec = tween(350)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300)
            )
        }) {
//        composable(ScreenNavigator.IntroScreen.name){
//            IntroScreen(navHostController)
//        }
//
//        composable(ScreenNavigator.SplashScreen.name){
//            SplashScreen(navHostController)
//        }
        composable(ScreenNavigator.TopHeadlineScreen.name) {
            val viewModel = getViewModel<TopHeadlineViewModel>()
            TopHeadlineScreen(paddingValues, navHostController, viewModel)
        }
        composable(ScreenNavigator.AllNewsScreen.name) {
            val viewModel = getViewModel<AllNewsViewModel>()
            AllNewsScreen(
                paddingValues = paddingValues,
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(ScreenNavigator.FilteredNewsScreen.name) {
            val viewModel = getViewModel<FilteredNewsViewModel>()
            FilteredNewsScreen(
                paddingValues = paddingValues,
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(ScreenNavigator.SearchNewsScreen.name) {
            val viewModel = getViewModel<SearchNewsViewModel>()
            SearchNewsScreen(
                paddingValues = paddingValues,
                navController = navHostController,
                viewModel = viewModel
            )
        }

    }
}