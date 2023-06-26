package com.soarbh.trulynews.ui.component

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.theme.spacing

@Composable
fun LottieError(modifier: Modifier) {
    Lottie(
        modifier = modifier,
        lottieFile = R.raw.error_animination,
        lottieTitle = R.string.error_text
    )
}

@Composable
fun LottieLoading(modifier: Modifier) {
    Lottie(
        modifier = modifier,
        lottieFile = R.raw.loading_animination,
        lottieTitle = R.string.loading_text
    )
}

@Composable
fun LottieEmpty(modifier: Modifier) {
    Lottie(
        modifier = modifier,
        lottieFile = R.raw.empty_animination,
        lottieTitle = R.string.no_data_to_show
    )
}

@Composable
fun Lottie(
    modifier: Modifier,
    @RawRes lottieFile: Int,
    @StringRes lottieTitle: Int,
    lottieAnimHeight: Dp = MaterialTheme.spacing.space300
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        LottieAnim(
            modifier = Modifier
                .fillMaxWidth()
                .height(lottieAnimHeight)
                .padding(top = MaterialTheme.spacing.space100),
            lottieFile = lottieFile
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.space48)
        )

        Text(
            text = stringResource(id = lottieTitle),
            modifier = Modifier.padding(top = MaterialTheme.spacing.space16),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

    }
}

@Composable
fun LottieAnim(
    modifier: Modifier,
    @RawRes lottieFile: Int
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieFile)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedFilter(
    modifier: Modifier,
    @StringRes filterItem: Int,
    onFilterItemClicked: () -> Unit
) {
    OutlinedCard(onClick = onFilterItemClicked, modifier = modifier) {
        Text(
            text = stringResource(id = filterItem), modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.space12,
                vertical = MaterialTheme.spacing.space6
            ), style = MaterialTheme.typography.titleMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedFilter(
    modifier: Modifier,
    filterItem: String,
    isItemSelected: Boolean,
    onFilterItemClicked: (String) -> Unit
) {
    OutlinedCard(onClick = { onFilterItemClicked(filterItem) }, modifier = modifier) {
        Row(
            Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.space12,
                    vertical = MaterialTheme.spacing.space6
                )
        ) {
            Text(text = filterItem, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.space8))
            Icon(
                imageVector = if (isItemSelected) Icons.Outlined.Check else Icons.Outlined.Add,
                contentDescription = "filter sign"
            )
        }
    }
}