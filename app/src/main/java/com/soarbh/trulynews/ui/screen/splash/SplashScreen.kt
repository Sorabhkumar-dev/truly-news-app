package com.soarbh.trulynews.ui.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.screen.navigation.ScreenNavigator
import com.soarbh.trulynews.ui.theme.spacing
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        val rocketComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rocket))
        val rocketProgress by animateLottieCompositionAsState(rocketComposition,  iterations = LottieConstants.IterateForever)

        val infiniteLoopComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.infinity_loop))
        val infiniteProgress by animateLottieCompositionAsState(infiniteLoopComposition, iterations = LottieConstants.IterateForever )

        LottieAnimation(
            composition = rocketComposition,
            progress = { rocketProgress },
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = MaterialTheme.spacing.space48)
        )

        LottieAnimation(
            composition = infiniteLoopComposition,
            progress = { infiniteProgress },
            modifier = Modifier
                .size(MaterialTheme.spacing.space128)
                .align(Alignment.BottomCenter)
        )

    }
}