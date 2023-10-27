import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        App()
    }
}