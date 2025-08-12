import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.imkaem.android.upuli.ui.theme.ColorGreyPink100

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UPuliTopAppBar(
    onNavigateBack: (() -> Unit)? = null,
    onNavigateToBookmarks: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(

        title = {},
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
                        contentDescription = "Back to previous screen",
                    )
                }
            }
        },
        actions = {
            if (onNavigateToBookmarks != null) {
                IconButton(
                    onClick = {
                        onNavigateToBookmarks.invoke()

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Bookmarks,
                        tint = ColorGreyPink100,
                        contentDescription = "Bookmarks Icon",
                    )
                }
            }
        }
    )
}