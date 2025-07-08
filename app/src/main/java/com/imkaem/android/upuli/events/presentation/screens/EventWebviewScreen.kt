package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import WebViewContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imkaem.android.upuli.events.presentation.view_models.EventScreenViewModel
import com.imkaem.android.upuli.events.presentation.view_models.EventWebViewScreenViewModel
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorWhite
import java.nio.file.WatchEvent

@Composable
fun EventWebViewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
) {

    val viewModel: EventWebViewScreenViewModel = viewModel()
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        },

        ) { padding ->
        /* TODO not sure if it is worth it to extract contnet into its own widget */


        val isLoading = state.isLoading
        if (isLoading) {
            LoadingIndicator(
                modifier = Modifier.padding(padding).fillMaxSize()
            )
            /* TODO is this ok? to return from scaffold */
            return@Scaffold
        }

        val url = state.url
        if (url == null) {
            Column(
                modifier = Modifier.padding(padding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    "Hm, nešto nije u redu. Ne možemo prikazati detalje događaja... :(",
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Center,
                    color = ColorWhite,
                    fontSize = 12.sp
                )
            }
            return@Scaffold
        }



        Column(
            modifier = Modifier.padding(padding)
        ) {




            WebViewContent(
//                url = "https://www.gkc-pula.hr/hr/dogadjanja/4450/ljeto-u-knjiznici-ljetne-price-za-ljepse-snove/",
                url = url,
            )
        }

    }
}
