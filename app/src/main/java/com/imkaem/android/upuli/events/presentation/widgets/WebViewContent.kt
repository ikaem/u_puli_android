import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

/* TODO we should convert this to a widget instead, and reuse wherever webview is needed */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewContent(
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
        factory = { context ->

            WebView(context).apply {
                /* TODO maybe not good to use this - will see */
                /* TODDO required for ink*/
                        settings.javaScriptEnabled = true
//                        settings.loadWithOverviewMode = true // -> maybe should not set this
//                        settings.useWideViewPort = true // not sure if this should be used
//                        settings.setSupportZoom(true)
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webViewClient = WebViewClient()

                /* TODO could this be moved above? */
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )

}