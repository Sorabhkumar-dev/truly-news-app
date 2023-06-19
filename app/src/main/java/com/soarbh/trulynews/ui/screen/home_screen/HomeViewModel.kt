package com.soarbh.trulynews.ui.screen.home_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Tune
import androidx.lifecycle.ViewModel
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.screen.navigation.ScreenNavigator
import com.sorabh.data.pojo.other.DrawerItem
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel :ViewModel() {
    val drawerItems = listOf(
        DrawerItem(
            Icons.Outlined.Feed, R.string.top_headline,
            ScreenNavigator.TopHeadlineScreen.name),
        DrawerItem(Icons.Outlined.Language, R.string.all_news, ScreenNavigator.AllNewsScreen.name),
        DrawerItem(Icons.Outlined.Tune, R.string.filtered_news, ScreenNavigator.FilteredNewsScreen.name)
    )

    val selectedDrawerItem = MutableStateFlow(drawerItems.first())
}