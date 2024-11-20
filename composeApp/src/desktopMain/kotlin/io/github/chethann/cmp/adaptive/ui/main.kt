package io.github.chethann.cmp.adaptive.ui

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMPAdaptiveUIPlayground",
    ) {
        App()
    }
}