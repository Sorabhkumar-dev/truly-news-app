package com.soarbh.trulynews.ui.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.soarbh.trulynews.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            TopNavigationDrawer(modifier = Modifier.fillMaxWidth())
            NavigationDrawerItem(label = { Text(text = "Home") }, selected = false, onClick = { })
        }
    },drawerState = drawerState) {
        Text(text = "SORABH is legend")
    }
}

@Composable
fun TopNavigationDrawer(modifier: Modifier) {
    Row(modifier) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "drawer menu icon")
        Text(text = stringResource(id = R.string.app_name))
        Divider()
    }
}