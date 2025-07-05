import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UPuliTopAppBar(
    onNavigateBack: (() -> Unit)? = null,
    onNavigateToBookmarks: (() -> Unit)? = null,
    title: String = "",
) {
    CenterAlignedTopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            if (onNavigateBack == null) {
                null
            } else {
                IconButton(
                    onClick = {
                        onNavigateBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to previous screen"
                    )
                }
            }
        },
        actions = {
//            IconButton(
//                onClick = {
//
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Bookmark,
//                    contentDescription = "Favorites",
//                )
//            }
            if (onNavigateToBookmarks != null) {
                IconButton(
                    onClick = {
                        onNavigateToBookmarks.invoke()

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Bookmarks,
                        contentDescription = "Favorites",
                    )
                }

            }
            /* TODO temp nothing here, since no settings yet ... */
//            IconButton(
//                onClick = {
//
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Settings,
//                    contentDescription = "Settings",
//                )
//            }


        }
    )
}