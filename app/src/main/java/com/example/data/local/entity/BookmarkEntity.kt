package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookId: String,
    val bookTitle: String,
    val chapterIndex: Int,
    val chapterTitle: String,
    val excerpt: String,
    val userNote: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
