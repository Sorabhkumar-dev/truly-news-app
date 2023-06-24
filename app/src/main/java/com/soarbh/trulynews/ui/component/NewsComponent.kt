package com.soarbh.trulynews.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.theme.spacing
import com.sorabh.data.pojo.response.Article
import com.sorabh.data.util.DateUtil

@Composable
fun NewsItemCard(modifier: Modifier, article: Article) {
    ElevatedCard(modifier) {
        Column(Modifier.fillMaxWidth()) {
            ShowImageFromUrl(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(
                        start = MaterialTheme.spacing.space8,
                        top = MaterialTheme.spacing.space16,
                        end = MaterialTheme.spacing.space8
                    ).clip(MaterialTheme.shapes.medium),
                imageUrl = article.urlToImage ?: "",
                description = article.title ?: ""
            )

            Row(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.space8,
                        top = MaterialTheme.spacing.space8,
                        end = MaterialTheme.spacing.space8
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = article.title ?: stringResource(id = R.string.label_na),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(3f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    text = article.publishedAt?.let {
                        DateUtil.getUserFormat(
                            DateUtil.getLocalDateTimeFormServerDate(it)
                        )
                    } ?: stringResource(id = R.string.label_na),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = article.description ?: stringResource(id = R.string.label_na),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.space8,
                        top = MaterialTheme.spacing.space8,
                        end = MaterialTheme.spacing.space8
                    ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.source?.name ?: stringResource(id = R.string.label_na),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.space8,
                        top = MaterialTheme.spacing.space8,
                        end = MaterialTheme.spacing.space8
                    )
            )

            Text(text = article.author?.let { "- $it" } ?: stringResource(id = R.string.label_na),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.space8,
                    top = MaterialTheme.spacing.space8,
                    end = MaterialTheme.spacing.space8,
                    bottom = MaterialTheme.spacing.space16
                )
            )

        }
    }
}


@Composable
fun ShowImageFromUrl(modifier: Modifier, imageUrl: String, description: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        error = painterResource(id = R.drawable.error_image),
        contentDescription = description ?: "",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}