package com.imkaem.android.upuli.search.presentation.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.imkaem.android.upuli.events.presentation.widgets.LoadingIndicator
import com.imkaem.android.upuli.ui.theme.ColorYellowDark
import com.imkaem.android.upuli.ui.theme.ColorYellowLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    searchQuery: String,
    isLoading: Boolean,
    onChangeSearchQuery: (String) -> Unit,
    onSubmitSearchQuery: () -> Unit,
) {

//    Text("Hekll")
//    SearchBar(
//        query = searchQuery,
//        onQueryChange = onChangeSearchQuery,
//        onSearch = {},
//        placeholder = {
//            Text("Search")
//        },
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Filled.Search,
//                contentDescription = "Search icon"
//            )
//        },
//        modifier = Modifier.padding(),
//        content = {},
//        active = TODO(),
//        onActiveChange = TODO(),
//        enabled = TODO(),
//        trailingIcon = TODO(),
//        shape = TODO(),
//        colors = TODO(),
//        tonalElevation = TODO(),
//        shadowElevation = TODO(),
//        windowInsets = TODO(),
//        interactionSource = TODO()
//    )

//    SearchBar(
//        query = searchQuery,
//        onQueryChange = onChangeSearchQuery,
//        onSearch = {},
//        placeholder = {
//            Text("Search")
//        },
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Filled.Search,
//                contentDescription = "Search icon"
//            )
//        },
//        content = {}
//    )
/*sources: */
//https://medium.com/@a.poplawski96/implement-modern-search-functionality-on-android-with-compose-mvvm-clean-architecture-junit5-898fb30d9792
// also useful for keyboard action
//    https://canopas.com/keyboard-handling-in-jetpack-compose-all-you-need-to-know-3e6fddd30d9a
//    https://medium.com/@a.poplawski96/implement-modern-search-functionality-on-android-with-compose-mvvm-clean-architecture-junit5-61cbbee963ba

    TextField(
        value = searchQuery,
        enabled = !isLoading,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions (
            onSearch = {
                onSubmitSearchQuery()
            }
        ),
        onValueChange = onChangeSearchQuery,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = ColorYellowLight,
            focusedContainerColor = ColorYellowLight,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                tint = ColorYellowDark,
                modifier = Modifier
                    .size(24.dp),
                contentDescription = "Search"
            )
        },
        placeholder = { Text("Search...") },
    )
}