package com.example.ui.navigation

sealed interface Screen {
    object Home : Screen
    object Library : Screen
    object Search : Screen
    data class BookDetail(val bookId: String) : Screen
    data class Reader(val bookId: String, val chapterIndex: Int = 0) : Screen
    object Bookmarks : Screen
    object Settings : Screen
}
