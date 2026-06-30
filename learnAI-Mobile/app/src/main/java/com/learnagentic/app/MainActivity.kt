package com.learnagentic.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.learnagentic.core.ui.theme.LearnAgenticTheme

/**
 * Single Activity host for all Compose navigation screens.
 * Edge-to-edge rendering enabled by default.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnAgenticTheme {
                LearnAgenticNavHost()
            }
        }
    }
}
