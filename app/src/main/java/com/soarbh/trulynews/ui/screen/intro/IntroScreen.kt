package com.soarbh.trulynews.ui.screen.intro

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.screen.navigation.ScreenNavigator
import com.soarbh.trulynews.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutine = rememberCoroutineScope()
    val lottieAnimationFiles = remember {
        mutableStateListOf(R.raw.football, R.raw.singing_contract, R.raw.email_marketing)
    }

    var linearProgressFloat by remember { mutableStateOf(0f) }

    val linearProgress = animateFloatAsState(linearProgressFloat)

    linearProgressFloat = when (pagerState.currentPage) {
        0 -> 0.25f
        1 -> 0.50f
        2 -> 0.75f
        else -> 1f
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LinearProgressIndicator(
            progress = linearProgress.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.space16,
                    top = MaterialTheme.spacing.space48,
                    end = MaterialTheme.spacing.space16
                )
        )

        HorizontalPager(
            pageCount = 4,
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.space500)
                .padding(
                    start = MaterialTheme.spacing.space16,
                    top = MaterialTheme.spacing.space24,
                    end = MaterialTheme.spacing.space16
                ),
            state = pagerState
        ) { index ->
            if (index == 0)
                IntroSlide(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(
                            start = MaterialTheme.spacing.space16,
                            top = MaterialTheme.spacing.space24,
                            end = MaterialTheme.spacing.space16
                        )
                )
            else {

                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        lottieAnimationFiles[index - 1]
                    )
                )

                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    composition = composition,
                    progress = { progress })
            }
        }

        ElevatedButton(
            onClick = {
                coroutine.launch {
                    if (pagerState.currentPage < 3) {
                        pagerState.scrollToPage(pagerState.currentPage.plus(1))
                    }
                  else navController.navigate(ScreenNavigator.TopHeadlineScreen.name)
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.space16,
                    top = MaterialTheme.spacing.space24,
                    end = MaterialTheme.spacing.space16
                )
        ) {
            Text(
                text = when (pagerState.currentPage) {
                    0 -> stringResource(id = R.string.lets_start)
                    1 -> stringResource(id = R.string.whats_more)
                    2 -> stringResource(id = R.string.get_up_to_date)
                    else -> stringResource(id = R.string.ok_get_my_news)
                },
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "forward icon",
                modifier = Modifier.padding(start = MaterialTheme.spacing.space8)
            )
        }
    }
}

@Composable
fun IntroSlide(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.news_on_the_go),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(id = R.string.start_to_get_updated_with_latest_news),
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.space16),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}