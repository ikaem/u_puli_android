import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun EventWebView(
    url: String,
) {

    /* TODO probably need some view model to accept argument or url and access it here */
    /* will probably need to override back? or will i? I am not sure */

    /*
    * https://www.geeksforgeeks.org/webview-in-android-using-jetpack-compose/
    * https://medium.com/@sahar.asadian90/webview-in-jetpack-compose-71f237873c2e
    * https://medium.com/@kevinnzou/using-webview-in-jetpack-compose-bbf5991cfd14
    * */

    AndroidView(
        factory = { it ->
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()

                loadUrl(url)
            }
        },
        update = { it ->
            it.loadUrl(url)
        }
    )

}