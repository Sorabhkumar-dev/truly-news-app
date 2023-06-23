package com.soarbh.trulynews.ui.screen.home_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.soarbh.trulynews.R
import com.soarbh.trulynews.ui.screen.navigation.AppNavigation
import com.soarbh.trulynews.ui.screen.navigation.ScreenNavigator
import com.soarbh.trulynews.ui.theme.spacing
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = get()
    val navController = rememberAnimatedNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                TopNavigationDrawer(modifier = Modifier.fillMaxWidth())
                viewModel.drawerItems.forEach { drawerItem ->
                    NavigationDrawerItem(label = { Text(text = stringResource(id = drawerItem.drawerTitle)) },
                        icon = {
                            Icon(
                                imageVector = drawerItem.drawerIcon,
                                contentDescription = "${stringResource(id = drawerItem.drawerTitle)} icon"
                            )
                        },
                        selected = viewModel.selectedDrawerItem.collectAsStateWithLifecycle().value == drawerItem,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            viewModel.selectedDrawerItem.value = drawerItem
                            navController.navigate(drawerItem.screen)
                        })
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        gesturesEnabled = !navController.currentBackStackEntryAsState().value?.destination?.route.equals(
            ScreenNavigator.SplashScreen.name
        ) && !navController.currentBackStackEntryAsState().value?.destination?.route.equals(
            ScreenNavigator.IntroScreen.name
        ),
        drawerState = drawerState
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            if (
                !navController.currentBackStackEntryAsState().value?.destination?.route.equals(
                    ScreenNavigator.SplashScreen.name
                ) && !navController.currentBackStackEntryAsState().value?.destination?.route.equals(
                    ScreenNavigator.IntroScreen.name
                )
            )
                TrulyTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    coroutineScope.launch {
                        if (drawerState.isClosed) drawerState.open()
                        else drawerState.close()
                    }
                }
        }, floatingActionButton = {
            if (!navController.currentBackStackEntryAsState().value?.destination?.route.equals(
                    ScreenNavigator.SearchNewsScreen.name
                ) && !navController.currentBackStackEntryAsState().value?.destination?.route.equals(
                    ScreenNavigator.SplashScreen.name
                ) && !navController.currentBackStackEntryAsState().value?.destination?.route.equals(
                    ScreenNavigator.IntroScreen.name
                )
            ) FloatingActionButton(onClick = {
                navController.navigate(ScreenNavigator.SearchNewsScreen.name)
            }) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.space8)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = " News Search Icon button",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = stringResource(id = R.string.search_news),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                }
            }
        }) {
            AppNavigation(paddingValues = it, navHostController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrulyTopBar(modifier: Modifier, onNavigationIconClicked: () -> Unit) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
        )
    }, modifier = modifier, navigationIcon = {
        IconButton(onClick = onNavigationIconClicked) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "navigation icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    })
}

@Composable
fun TopNavigationDrawer(modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.truely_news_logo),
            contentDescription = "drawer menu icon",
            modifier = Modifier
                .height(MaterialTheme.spacing.space128)
                .padding(top = MaterialTheme.spacing.space24)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.space16))
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.space16)
        )
    }
}