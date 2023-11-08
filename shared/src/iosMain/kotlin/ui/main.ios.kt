package ui

import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        App()
    }
}