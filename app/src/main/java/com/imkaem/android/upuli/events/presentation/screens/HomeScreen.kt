package com.imkaem.android.upuli.events.presentation.screens

import HomeScreenContent
import UPuliTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.HomeScreenViewModel
import com.imkaem.android.upuli.ui.theme.UPuliTheme

/* TODO this screen, all its widgets, view model, state, and so on, should be renamed to HomeScreen */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToEvent: (id: Int) -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToTodayEvents: () -> Unit,
    onNavigateToTomorrowEvents: () -> Unit,
) {

    val viewModel: HomeScreenViewModel = viewModel()
    val screenState = viewModel.state.collectAsStateWithLifecycle().value

    /* TODO not sure if this should have its own view model? */
//    val selectedTabIndex = remember {
//        mutableIntStateOf(0)
//    }
//
//    Scaffold(
//        topBar = {
//            UPuliTopAppBar(
//                onNavigateToBookmarks = onNavigateToBookmarks,
//            )
//        },
//
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier.padding(paddingValues)
//        ) {
//            TabRow(
//                selectedTabIndex = selectedTabIndex.intValue,
//
//                ) {
//
//                Tab(
//                    selected = selectedTabIndex.intValue == 0,
//                    onClick = {
//                        selectedTabIndex.intValue = 0
//                    },
//                    text = {
//                        Text("Uskoro")
//                    }
//                )
//
//                Tab(
//                    selected = selectedTabIndex.intValue == 1,
//                    onClick = {
//                        selectedTabIndex.intValue = 1
//                    },
//                    text = {
//                        Text("Svi događaji")
//                    }
//                )
//            }
//
//            selectedTabIndex.let { it ->
//                when (it.intValue) {
//                    0 -> {
//                        Text("Uskoro", modifier = Modifier.padding(16.dp))
//                    }
//                    else ->
//                        Text("Svi događaji", modifier = Modifier.padding(16.dp))
//                }
//
//
//            }
//        }
//    }


    /* TOOD real content */
    Scaffold(
        /* TODO not sure if this contributes to anything in this particular case */
//        containerColor = Color.Yellow,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        },
        content = { padding ->
            HomeScreenContent(
                screenState = screenState,
                onNavigateToEvent = onNavigateToEvent,
                onNavigateToTodayEvents = onNavigateToTodayEvents,
                onNavigateToTomorrowEvents = onNavigateToTomorrowEvents,
                onToggleEventIsBookmarked = viewModel::onToggleEventIsBookmarked,
                onSelectTab = viewModel::onSelectTab,
                padding = padding,
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    UPuliTheme {
        HomeScreen(
            onNavigateToEvent = {  /* no-op */ },
            onNavigateToTodayEvents = { /* no-op */ },
            onNavigateToBookmarks = { /* no-op */ },
            onNavigateToTomorrowEvents = { /* no-op */ }
        )
    }
}