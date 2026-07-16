package com.example.capsulasgaribaldi

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.capsulasgaribaldi.ui.theme.CapsulasGaribaldiTheme
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        CapsulasGaribaldiTheme {
            CatalogoCafesScreen()
        }
    }
}
