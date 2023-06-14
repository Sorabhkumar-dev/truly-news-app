package com.soarbh.trulynews.ui.screen.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.soarbh.trulynews.ui.screen.home.HomeScreen
import com.soarbh.trulynews.ui.screen.home.HomeViewModel
import com.soarbh.trulynews.ui.screen.intro.IntroScreen
import com.soarbh.trulynews.ui.screen.splash.SplashScreen
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navHostController: NavHostController) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = ScreenNavigator.IntroScreen.name,
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
        composable(ScreenNavigator.IntroScreen.name){
            IntroScreen(navHostController)
        }

        composable(ScreenNavigator.SplashScreen.name){
            SplashScreen(navHostController)
        }
        composable(ScreenNavigator.HomeScreen.name){
            val viewModel = getViewModel<HomeViewModel>()
            HomeScreen(navHostController,viewModel)
        }
    }
}