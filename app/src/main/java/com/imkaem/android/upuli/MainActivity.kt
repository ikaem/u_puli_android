package com.imkaem.android.upuli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imkaem.android.upuli.events.presentation.screens.BookmarkedEventsScreen
import com.imkaem.android.upuli.events.presentation.screens.TodayEventsScreen
import com.imkaem.android.upuli.events.presentation.screens.EventScreen
import com.imkaem.android.upuli.events.presentation.screens.EventWebViewScreen
import com.imkaem.android.upuli.events.presentation.screens.HomeScreen
import com.imkaem.android.upuli.events.presentation.screens.TomorrowEventsScreen
import com.imkaem.android.upuli.ui.theme.ColorGreyGreen100
import com.imkaem.android.upuli.ui.theme.UPuliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UPuliTheme {
                Surface(
                    /* TODO this is to avoid flashing default color (white) when we change screens */
                    color = ColorGreyGreen100,
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


/* TODO extract this to its own file */
@Composable
fun UPuliApp() {
    val navController: NavHostController = rememberNavController()

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

    NavHost(
        startDestination = "home",
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
    }

}