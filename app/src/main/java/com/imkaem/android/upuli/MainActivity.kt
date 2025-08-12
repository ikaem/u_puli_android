package com.imkaem.android.upuli

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imkaem.android.upuli.events.presentation.screens.BookmarkedEventsScreen
import com.imkaem.android.upuli.events.presentation.screens.TodayEventsScreen
import com.imkaem.android.upuli.events.presentation.screens.EventScreen
import com.imkaem.android.upuli.events.presentation.screens.EventWebViewScreen
import com.imkaem.android.upuli.events.presentation.screens.HomeScreen
import com.imkaem.android.upuli.events.presentation.screens.TomorrowEventsScreen
import com.imkaem.android.upuli.search.presentation.screens.SearchScreen
import com.imkaem.android.upuli.ui.theme.UPuliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UPuliTheme {
                Surface(
                    /* TODO this is to avoid flashing default color (white) when we change screens */
//                    color = ColorGreyGreen100,
                    modifier = Modifier.fillMaxSize()
                        /* TODO for some reason, this has no effect */
//                        .background(Color.Yellow)

                ) {
                    UPuliApp()
                }
            }
        }
    }
}

/* TODO temp here */
enum class NavigationBarDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String,
) {
    HOME(
        route = "home",
        label = "Početna",
        icon = Icons.Filled.Home,
        contentDescription = "Home"
    ),
    SEARCH(
        route = "search",
        label = "Pretraga",
        icon = Icons.Filled.Search, // TODO replace with search icon
        contentDescription = "Search"
    ),
}


/* TODO extract this to its own file */
@Composable
fun UPuliApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val startDestination = NavigationBarDestination.HOME
    val selectedDestination = rememberSaveable { mutableIntStateOf(0) }

    fun onNavigateToBookmarks() {
        navController.navigate("bookmarked_events")
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToEvent(id: Int) {
        navController.navigate("events/$id")
    }

    fun navigateToTodayEvents() {
        navController.navigate("today_events")
    }

    fun navigateToTomorrowEvents() {
        navController.navigate("tomorrow_events")
    }

    fun onNavigateToEventWebView(id: Int) {
        /* TODO eventually, we will do this */
        navController.navigate("events/$id/webview")

        /* TODO this is only temp */
//        navController.navigate("event_webview")
    }
/* we should only show bottom navigation bar on home screen
* https://stackoverflow.com/a/78619222/9661910
* or we can render it on home screen only? i dont know yet
* */
    Scaffold(
        bottomBar = {
            val currentDestination = navBackStackEntry.value?.destination?.route
            val shouldDisplayBottomNavigationBar = when(currentDestination) {
                "home" -> true
                "search" -> true
                else -> false // TODO maybe we should show it on search?
            }

            if(!shouldDisplayBottomNavigationBar) return@Scaffold

            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                NavigationBarDestination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination.intValue == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination.intValue = index
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
//                        label = { Text(destination.label)}
                    )

                }

            }
        }
    ) { padding ->
        /* TODO temp */
        Log.d("UPuliApp", "Padding values: $padding")
        Box(
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
        ) {
            NavHost(
                //            startDestination = "home",
                startDestination = startDestination.route,
                navController = navController,
            ) {
                composable(
                    route = "home"
                ) {
                    HomeScreen(
                        onNavigateToEvent = ::navigateToEvent,
                        onNavigateToBookmarks = ::onNavigateToBookmarks,
                        onNavigateToTodayEvents = ::navigateToTodayEvents,
                        onNavigateToTomorrowEvents = ::navigateToTomorrowEvents,
                    )
                }

                composable(
                    route = "bookmarked_events"
                ) {
                    BookmarkedEventsScreen(
                        onNavigateBack = ::navigateBack,
                        onNavigateToEvent = ::navigateToEvent,
                    )
                }

                composable(
                    route = "events/{event_id}",
                    arguments = listOf(
                        navArgument("event_id") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    EventScreen(
                        onNavigateBack = {
                            navigateBack()
                        },
                        onNavigateToBookmarks = {
                            onNavigateToBookmarks()
                        },
                        onNavigateToEventWebView = { id ->
                            onNavigateToEventWebView(id)
                        },
                    )
                }

                composable(
                    route = "today_events",
                ) {
                    TodayEventsScreen(
                        onNavigateToEvent = ::navigateToEvent,
                        onNavigateBack = ::navigateBack,
                        onNavigateToBookmarks = ::onNavigateToBookmarks,
                    )
                }

                composable(
                    route = "tomorrow_events",
                ) {
                    TomorrowEventsScreen(
                        onNavigateToEvent = ::navigateToEvent,
                        onNavigateBack = ::navigateBack,
                        onNavigateToBookmarks = ::onNavigateToBookmarks,
                    )
                }

                composable(
                    //            /* TODO eventually, we will do this */
                    route = "events/{event_id}/webview",
                    arguments = listOf(
                        navArgument("event_id") {
                            type = NavType.IntType
                        }
                    )
                    /* TODO this is only temp */
                    //            route = "event_webview"
                ) {
                    EventWebViewScreen(
                        onNavigateBack = ::navigateBack,
                        onNavigateToBookmarks = ::onNavigateToBookmarks,
                    )
                }

                composable(
                    route = "search",
                ) {
                    SearchScreen(
                        onNavigateToEvent = ::navigateToEvent,
                        onNavigateBack = ::navigateBack,
                        onNavigateToBookmarks = ::onNavigateToBookmarks
                    )
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                    ) {
//                        Text(
//                            text = "Search screen",
//                            modifier = Modifier.fillMaxSize(),
//                        )
//                    }
                }
            }
        }
    }

}