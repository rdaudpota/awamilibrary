package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.navigation.Screen
import com.example.ui.screens.BookDetailScreen
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ReaderScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AwamiLibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AwamiLibraryApp()
                }
            }
        }
    }
}

@Composable
fun AwamiLibraryApp(
    viewModel: AwamiLibraryViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()

    BackHandler(enabled = currentScreen !is Screen.Home) {
        viewModel.navigateBack()
    }

    Box(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
        when (currentScreen) {
            is Screen.Home -> {
                HomeScreen(viewModel = viewModel)
            }
            is Screen.BookDetail -> {
                BookDetailScreen(viewModel = viewModel)
            }
            is Screen.Reader -> {
                ReaderScreen(viewModel = viewModel)
            }
            is Screen.Bookmarks -> {
                BookmarksScreen(viewModel = viewModel)
            }
            is Screen.Settings -> {
                SettingsScreen(viewModel = viewModel)
            }
            is Screen.Search -> {
                HomeScreen(viewModel = viewModel)
            }
            is Screen.Library -> {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}
