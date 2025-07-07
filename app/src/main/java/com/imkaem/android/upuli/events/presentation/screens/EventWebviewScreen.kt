package com.imkaem.android.upuli.events.presentation.screens

import UPuliTopAppBar
import WebViewContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EventWebViewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToBookmarks: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UPuliTopAppBar(
                onNavigateBack = onNavigateBack,
                onNavigateToBookmarks = onNavigateToBookmarks,
            )
        },

    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            WebViewContent(
                url = "https://www.gkc-pula.hr/hr/dogadjanja/4450/ljeto-u-knjiznici-ljetne-price-za-ljepse-snove/",
            )
        }

    }
}
