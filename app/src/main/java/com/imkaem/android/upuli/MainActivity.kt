package com.imkaem.android.upuli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imkaem.android.upuli.events.presentation.screens.BookmarkedEventsScreen
import com.imkaem.android.upuli.events.presentation.screens.EventScreen
import com.imkaem.android.upuli.events.presentation.screens.EventsScreen
import com.imkaem.android.upuli.ui.theme.UPuliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UPuliTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
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

    NavHost(
        startDestination = "events",
        navController = navController,
    ) {
        composable(
            route = "events"
        ) {
            EventsScreen(
                onNavigateToEvent = ::navigateToEvent,
                onNavigateToBookmarks = ::onNavigateToBookmarks,
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
                }
            )
        }
    }

}